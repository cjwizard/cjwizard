# Introduction #

The best approach (for now) is to look at the [https://github.com/creswick/cjwizard/blob/master/src/org/ciscavate/cjwizard/WizardTest.java WizardTest] class in svn and see how it creates a simple wizard.  That class is well commented, and uses the core set of features in CJWizard.

## Quick Start ##

WizardContainer is the widget that contains the wizard itsself, so your first step is to determine where that widget will go (it is a subclass of JPanel).  WizardTest places the WizardContainer directly in the content pane of a JDialog, which is a pretty common use case.  You could just as easily embed it in a larger window, or some other application if you want, though.

```
   // create the WizardContainer:
   final PageFactory pageFactory = /* create a PageFactory, see below */;
   final WizardContainer wizard = new WizardContainer(pageFactory)
   
   // stick the WizardContainer into a dialog:
   final JDialog dialog = new JDialog();
   dialog.getContentPane().add(wizard);
   dialog.pack();
   dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
   dialog.setVisible(true);
```


### PageFactory ###

The PageFactory interface defines the API that the WizardContainer uses to retrieve pages for the dialog.  It has one method: `createPage(...)`:

```
public interface PageFactory {

   /**
    * Creates (or retrieves) a wizard page based on the path of pages covered
    * so far between now and the start of the dialog, and the map of settings.
    * 
    * @param path  The list of all WizardPages seen so far.
    * @param settings The Map of settings collected.
    * @return The next WizardPage.
    */
   public WizardPage createPage(List<WizardPage> path, WizardSettings settings);
}
```

`createPage(...)` takes two parameters: a List of the WizardPages that have been seen thus far, and a WizardSettings structure (essentially a complex `Map<String, Object>`, although at this point it does not implement the full Map interface).  The settings map holds all the data collected thus far in the wizard.

`createPage(...)` is called from whenever the WizardContainer needs a new WizardPage to display.  It is *not* invoked when moving backwards, but any time someone presses "Next >", they will get what `createPage(...)` returns (barring some bizarre or broken PageTemplate, but that's a topic for later).

This gives you almost complete control over the wizards behavior -- based on the settings and the current location you can determine any flow you want.

### WizardPage ###
The actual pages of questions or information in the Wizard are instances of WizardPage.  This is another subclass of JPanel, along with some additional capabilities.  You will be creating a new implementation of WizardPage for each page in your wizard (go figure).  In many cases, you don't need to do anything other than create the components you want to use, call `setName(someUniqueName)` on the input widgets, and then add them to the WizardPage (it's easy to do this in the constructor).  WizardPage listens to component events to see when widgets are added, and checks each component for a name.  The named components are saved, and when the page is done (eg: when the user clicks 'Next >') the components are queried for their values, which are stored in the current WizardSettings object, keyed on the names of the components that those values were taken from.

If you are using custom components, then either override `WizardPage.updateSettings(...)` or make your components implement `CustomWizardComponent.getValue()`

### WizardListener ###

So how do you know when the wizard is finished? The WizardListener interface defines three methods that are invoked when common  wizard operations occurr:

```java
   /**
    * Invoked when the wizard changes to display a new page.
    * 
    * @param newPage  The new page displayed.
    * @param path The list of WizardPages shown so far.
    */
   public void onPageChanged(WizardPage newPage, List<WizardPage> path);
   
   /**
    * Invoked when the user clicks the Finish button.
    * 
    * @param path The list of WizardPages shown so far.
    * @param settings The collection of settings gleaned during the wizard.
    */
   public void onFinished(List<WizardPage> path, WizardSettings settings);
   
   /**
    * Invoked when the user clicks the Cancel button.
    * 
    * @param path The list of WizardPages shown so far.
    * @param settings The collection of settings gleaned during the wizard.
    */
   public void onCanceled(List<WizardPage> path, WizardSettings settings);
```

By implementing this interface, and passing an instance of the object into the `WizardController.addWizardListener(listener)` method, you can act in response to the core wizard actions.  Generally `onFinished(...)` will be implemented to collect the critical information from the WizardSettings object and flesh out a data structure that the rest of your application will work with.
