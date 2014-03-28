CJWizard is an API for creating wizard dialogs in Java.

This library was spurred by my use of the Java Wizard library at Java.net (https://wizard.dev.java.net/).  That library offers a good framework for simple wizards, but it dictates a great deal of what you can do--justified by the java usability guidelines.  However, I don't believe all the restrictions actually are justified by those guidelines.  For example, it is particularly difficult to change the type of widget used on the left side of the wizards (it is initially an image with the list of wizard pages displayed over top of it).  Making this area interactive, as a global navigation tool, is non-trivial.

I also found it to be extremely cumbersome to define complex wizards that change their branching behavior at runtime.  Generally, I found the Java Wizard API to be difficult to use and at times contradictory.  

That said, I learned a lot from the Java Wizard API, and some of those ideas have been incorporated into CJWizards (I had to call it something.)

Take a look at the [FAQ](docs/FAQ.md) and [QuickStartGuide](docs/quickstart.md) to get going, and send me questions if you have any.

## Screenshots ##

This shows CJWizard in action, with a custom PageTemplate (drawing the list of dialog pages on the left) that wraps around a !TitledPageTemplate (drawing the underlined title of the !WizardPage).  The Windows L&F was used to make it look like a native app.  (The Finish button is enabled because the surrounding app is robust to missing data, and allows for the user to come back to the wizard at a later date, if needed.  Generally, and by default, the Finish button is disabled if it is not explicitly enabled, which often happens in the `render()` method of the last !WizardPage)

![sample dialog](http://cjwizard.googlecode.com/files/cjwizard1.png)
