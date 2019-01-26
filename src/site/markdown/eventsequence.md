# Event: User clicks "Next >" #

The Next button is a widget in the `WizardContainer`.  

When the user clicks next, the currently displayed page (the last page
on the path) is asked to update the `WizardSettings`.

Next, the `PageFactory` is provided with the current list of
`WizardPages` that the user has seen (which is empty if the wizard was
just created) and the `WizardSettings` map.  The `PageFactory` must
determine the next `WizardPage` based on these inputs, create or
retrieve the page, and return it to the `WizardContainer`.

The `WizardContainer` is registered with the page returned from the
`PageFactory`, so that the page has access to enable/disable the
navigation buttons as needed, or make other queries should the need
arise.

Next, the path is updated, adding the new page to the end of the list
of pages visited.does not 

The size of the path is now used to determine if the "Prev" button
should be enabled or disabled (if there is no previous page, it is
disabled).

The new page is then told that it is about to be displayed by calling
`page.rendering(path, getSettings())`.  This gives the page the
opportunity to adjust it's content based on the current path and
settings without imposing on the `PageFactory`'s ability to cache pages.

Finally, the `PageTemplate` is given the new page, and the
`WizardListeners` are notified of the change.

# Event: User clicks "< Prev" #



# Event: User clicks "Finish" # 



# Event: User clicks "Cancel" #