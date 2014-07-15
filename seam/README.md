# TODO write something here

# Create package
- Use the following maven line to build a package without running the unit tests.
mvn -Dmaven.test.skip=true package

## TODO
- CODE -
# implement facelets component/template for counting articles
# Skin login page appropiatly (check other projects for neat design)
# Make wiki page creation wixard (check Spring JSF tutorial)
# Create readable URLs for articles/users
# Add tags ala Gmail
# Front page tag cloud
# Add comments ala Google Sites (allow to disable or only allow auth users)
# RSS feed for all/user/tag articles
# Buatify with richfaces (own JSF AJAX component, own facelets component)

- TEST -
# Selenium/HTMLUnit for test (make sure they can be rerun)
# Test that there is no access to raw xhtml (facelets) code
# Move NotNullOrEmpty {BaseTester, WebTester, SpringTester} into common pkg

- LINKS -
http://sl33p3r.free.fr/tutorials/rails/wiki/wiki-en.html
http://mguillem.wordpress.com/2007/10/29/webtest-vs-selenium-webtest-wins-13-5/
