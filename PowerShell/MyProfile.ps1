## paths ##
$env:VS_HOME = "$HOME/Documents/Visual Studio 2010/Projects"
$env:PS_HOME = "$HOME/Documents/DotNet/PowerShell"
# set startup folder
Set-Location "$env:VS_HOME"


## functions ##
function New-File([string]$name) {
	New-Item -type file $name
}

function Edit-File($filePath) {
	$editor = 'C:\Program` Files\Sublime` Text` 2\sublime_text.exe'
 	$cmd = "$editor $filePath"
 	invoke-expression $cmd
}

function Explore-Here() {
	Invoke-Item .
}


## aliases for my own functions ##
New-Alias touch New-File
New-Alias editor Edit-File
New-Alias open-here Explore-Here

## aliases for build in functionality ##
New-Alias which Get-Command
# Invoke-WebRequest requires PowerShell 3
New-Alias wget Invoke-WebRequest
