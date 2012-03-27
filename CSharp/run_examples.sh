#!/bin/bash
work_dir=$(cd `dirname $0` && pwd)

pushd $work_dir
    for exe_file in *.exe; do
        rm -rf $exe_file
    done

    for csharp_file in *.cs; do
        gmcs $csharp_file
        exe_file=$(echo "$csharp_file" | sed 's/\(.*\)\.cs$/\1\.exe/')
    
        echo -e "\nrunning $work_dir/$exe_file\n----"
        mono $exe_file
        echo -e "----\n"
    done
popd

