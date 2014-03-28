
# Design #

The Wizard is a nested UI-focused structure.  The outer-most layer is
the `WizardContainer` a class that extends `JPanel`, and can therefore
be inserted into any other Swing widget (such as a `JDialog` or
`JFrame`).  `WizardContainer` contains the four buttons ("< Prev"
"Next >" "Finish" and "Cancel") and a `PageTemplate`.

`PageTemplate` is another `JPanel` class that can be customized to add
wrapper widgets around the actual wizard dialog content.  The default
`PageTemplate` does nothing but show the specified `WizardPage`es.
This default `PageTemplate` (`DefaultPageTemplate`) can be nested in
custom `PageTemplate`s to easily reuse the logic needed to flip between
pages when adding wrapper content.