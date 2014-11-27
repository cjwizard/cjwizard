# FAQ #

Here you can read the more asked questions. Well, at least the ones
that we know the answer.

## What license is this project under - can it be used in a commercial project? ##

The code is license with Apache License 2.0 so yes, you can use it in
a commercial project. But you must give attribution.

Our intent is that CJWizard can be used in virtually any software
project without impinging on your business model, but we would like to
be attributed so others will more readily learn about the project and
(hopefully) help to improve it over time.

## How do I contribute to the project? ##

The easiest way to contribute to the project is to fork the github
repo, work locally, then submit pull requests that (ideally) reference
issues on the CJWizard issue tracker.

If you have non-source contributions (such as documentation, examples,
etc..), or using github dosen't work for you for any reason, feel free
to contact Rogan (creswick at gmail) and we will find a way to include
your work.

## How to translate the buttons? ##

Cjwizard used ResourceBundle to retrieve its locale-specific resources.
So if you want to translate the buttons, you only need to do this simple
steps:

1. Copy `src/org/ciscavate/cjwizard/i18n/cjwizard.properties` and rename
it like `cjwizard_XX.properties` where `XX` is your language code. For example
for Spnaish it will be `cjwizard_es.properties`.
2. Open it with a simple text editor like Notepad or Gedit.
3. Translate the text in the right side of `=` to your language.
4. Save the file.
5. You are done.

If you have translated the resources, we will be pleased to add them to cjwizard
if you tell us to do so.
