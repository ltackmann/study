## functions ##
function New-File([string]$Name) {
	New-Item -type file $Name
}

## aliases ##
# Invoke-WebRequest requires PowerShell 3
New-Alias wget Invoke-WebRequest
New-Alias touch New-File
New-Alias which Get-Command