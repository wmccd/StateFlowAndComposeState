# StateFlowAndComposeState

A really simple example of StateFlow vs ComposeState...just to compare how they are used and have examples of syntax

### Layers
app (di stuff)
uilayer (compose screens)
presentationlayer (view models)
repositorylayer (emits values up the stack)
datalayer (a datastore - one of the emits comes from here)

### Purpose
Two ViewModels created in the presentation layer and two Composable screens created in the UI layer.
One VM and Screen uses StateFlow
One VM and Screen uses ComposeState
Both VMs powered by the same Repository
