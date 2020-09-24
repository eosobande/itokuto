# Description
Create reactive ui in android programmatically. Get rid of the boring and layout.xml files.

# Installation
Add the dependency to your build.gradle

```groovy
    dependencies {
        implementation 'com.eosobande:itokuto:1.0.0'
    }
```

# Sample Application
[Itokuto sample application](https://github.com/eosobande/itokuto-sample-app) 

# How to use
## 1. Layout: create a new class and implement the Widget interface

```kotlin
    class HomeLayout(override val context: Context) : Widget {

        override val widget: Widget
            get() =
                RStack(context)
                    .invoke {

                        +Text(context, "Welcome to Itokuto!")
                            .params(
                                RStackParams()
                                    .centerInParent()
                                    .margin(10.dp)
                            )

                    }

    }
```

The widget val property ideally should be a computed property since we do not need a reference for 
the instance.
 
We get a relative layout with width and height of match_parent, and a centered text view with
width and height of wrap_content and 10 dp margin on all sides.

The + before the Text object means to add the the text as a child of the RStack.
Only stacks (view groups) have this function, - is the opposite and removes the widget

## 2. Merge: create a new class and implement the Merge interface
```kotlin

    class HomeFrames(override val context: Context) : Merge {

        override val widgets: Array<Widget>
            get() = arrayOf(

                Frame(context)
                    .background(Color.BLUE)
                    .backgroundTint(0x33FFFFFF.colorState) // extension to convert color int to color state list
                    .id(R.id.first_frame)
                    .params(CoordinatorParams(MATCH, MATCH, behave = true))
                    .padding(bottom = 10.dp),

                Frame(context)
                    .background(Color.GREEN)
                    .id(R.id.second_frame)
                    .params(CoordinatorParams(MATCH, MATCH, behave = true))
                    .padding(bottom = 10.dp),

                Frame(context)
                    .background(Color.YELLOW)
                    .id(R.id.third_frame)
                    .params(CoordinatorParams(MATCH, MATCH, behave = true))
                    .padding(bottom = 10.dp)

            )

    }

```

Equivalent of a merge tag of 3 FrameLayouts, each with a background color of blue, green, and
yellow respectively, and also having individual ids and padding bottom of 10 dp.

CoordinatorParams(MATCH, MATCH, behave = true) is a coordinator layout param, with match_parent on
both width and height, behave = true adds the app scrolling behaviour to each frame,
placing it below the app bar.

## 3. To include a merge object into an existing layout
```kotlin
    class HomeLayout(override val context: AppCompatActivity) : Widget {

        override val widget: Widget
            get() =
                Coordinator(context)
                    .invoke {

                        +AppBar(context)
                             .params(CoordinatorParams(MATCH))
                             .invoke {

                                 +ToolBar(context)
                                     .supportActionBar(this@HomeLayout.context)
                                     .minHeight(60.dp)
                                     .params(AppBarParams(MATCH, 60.dp)) // width of match_parent and height of 60 dp
                                     .elevation(3f.dp) // 3 dp elevation
                                     .background(drawable(R.drawable.round_bottom_corners)) // drawable() helper method fetching a drawable resource
                                     .navigationIcon(drawable(R.drawable.ic_upload))

                             }

                        +HomeFrames(context)

                    }

    }
```

Here we have a coordinator layout as our top level view, with an app bar layout containing a toolbar.
And we then include the frames in a merge object from No. 2 above

## 4. Reactivity, states, and binding
```kotlin
    class MyButton(override val context: Context) : Widget {

        private val text: State<String> = State("Enabled")
        private val buttonState: State<Boolean> = State(true)

        override val widget: Button
            get() =
                Button(context, text) // text is string value of the button, button text value updates automatically when text state value changes
                    .bind(buttonState) {
                        enable(it) // enable or disable the button whenever buttonState value changes
                        text(if (it) "Enabled" else "Disabled") // change the value of text state object
                    }
                    .onClick {
                        buttonState(!buttonState()) // toggle the boolean value of buttonState
                    }

    }
```

This button is bound to two state objects of String and Boolean types. Whenever the text state
object changes, the button text automatically updates, when the buttonState boolean changes,
we toggle the enabled state of the button and update the text object

## 5. Recyclerview, adapters, and view holders
```kotlin

    // contact data item

    data class Contact(
        val firstName: String,
        val lastName: String,
        val phoneNumber: String
    )

    // contact layout item

    class ContactItem(override val context: Context) : RecyclerAdapter.Item<Contact>() {

        override val widget: Widget
            get() =
                VStack(context) // VStack is vertical stack (column / vertical linear layout)
                    .background(
                        Rectangle()
                            .solidColor(Color.GREEN)
                            .rounded()
                            .ripple()
                            .rippleColor(Color.WHITE) // green rounded rectangle bg with white ripple
                    )
                    .params(LStackParams(MATCH)) // match_parent width
                    .padding(15.dp) // 15 dp padding
                    .invoke {

                        +Text(context)
                            .bind(data) { // data is the contact state object in the parent class
                                // it = value of data state object which is of Contact type
                                text("${it.firstName} ${it.lastName}") // whenever the object changes, update the name text view
                            }
                            .params(LStackParams(MATCH))

                        +Text(context)
                            .params(LStackParams().margin(top = 5.dp))
                            .bind(data) {
                                text(it.phoneNumber) // update the phone number text view
                            }

                    }

    }

    // contact adapter
    // You can use the pre-written custom adapter or create yours
    // this is an example of Itokuto's packaged recycler adapter

    class ContactsAdapter : RecyclerAdapter<Contact, ContactsAdapter.WidgetHolder>() {

        // supports filtering
        override fun onFilter(filter: String, data: Contact) =
            data.firstName.contains(filter, true) ||
            data.lastName.contains(filter, true) ||
            data.phoneNumber.contains(filter, true)

        // makes use of AsyncListDiffer

        override fun areItemsTheSame(old: Contact, new: Contact) = old == new

        override fun areContentsTheSame(old: Contact, new: Contact) = old == new

        // on item click

        override fun onItemClick(holder: Item, data: Contact) {
            // do something with the clicked contact object
            // you also have access to the widget/view holder instance
        }

        // create the widget holder / view holder here

        override fun createWidgetHolder(context: Context, viewType: Int) = WidgetHolder(ContactItem(context))

        // the widget holder / view holder class

        class WidgetHolder(item: ContactItem) : BaseItem<Contact, ContactItem>(item)

    }

    // the recycler page layout

    class ContactsListPage(override val context: Context) : Widget {

        private val adapter: ContactsAdapter = ContactsAdapter().apply {

            // adding items to the adapter

            add(listOf(
                Contact("Emmanuel", "Sobande", "+123456789"),
                Contact("Michael", "Sobande", "+2356789022"),
                Contact("John", "Doe", "+03447204248247"),
                Contact("Jane", "Smith", "+53893936404"),
            ))

            // available methods
            // clear, add one, add one at position, add list, add list at position,
            // replace one, replace list, remove, refresh, and filter(string)

        }

        override val widget: Button
            get() =
                Recycler(context)
                    .setItemViewCacheSize(20)
                    .view { // helper method to access the view directly
                        // this scope for the underlying recyclerview object
                        layoutManager = LinearLayoutManager(context)
                        // we're accessing the layoutManager property directly
                    }
                    .adapter(adapter)

    }

```

## 6. Activities & Fragments: setContentView and onCreateView
```kotlin

    class HomeActivity : AppCompatActivity(), PageStructure {
    
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(HomeLayout(this)) // extension method
        }
    
    }

    class HomeFragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? = view ?: HomeLayout(context!!).view() // fetches the view instance

    }

```

## 7. DrawableToolbox: Create drawables programmatically
[DrawableToolbox by duanhong169](https://github.com/duanhong169/DrawableToolbox)
The missing DrawableToolbox for Android. Create drawables programmatically and get rid of the boring and always repeated drawable.xml files.

Itokuto supports using programmatic drawables and this library from duanhong169 makes creating programmatic drawables super easy and quick

Example: a rounded rectangle with green solid color and a white ripple, this was used in No. 5 above
```kotlin
    Rectangle()
        .solidColor(Color.GREEN)
        .rounded()
        .ripple()
        .rippleColor(Color.WHITE)
```

## 8. Be Creative
Itokuto is very powerful and flexible. There's still a lot of possibilities and methods of usage but 
I couldn't put everything into this README document. 
If you have any problems using this library please create an issue and I'll respond swiftly.

THANK YOU!!!

## License

    Copyright 2020 Emmanuel Sobande

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.