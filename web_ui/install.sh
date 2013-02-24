find . -iname "packages" -exec rm -rf {} +
rm -rf .buildlog web/out 
pub install 
