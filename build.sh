#!/bin/bash

echo "================================================================="
echo "Build process started at: $(date)"
echo "================================================================="

echo "Running 'mvn clean install -DskipTests' in all subdirectories..."

for dir in */; do
    if [ -f "$dir/pom.xml" ]; then
        echo
        echo "==========================================="
        echo "Building $dir"
        echo "Start time: $(date)"
        echo "==========================================="
        
        cd "$dir" || exit
        mvn clean install -DskipTests
        cd ..
        
        echo "Finished building $dir at: $(date)"
    else
        echo "Skipping $dir (no pom.xml found)"
    fi
	echo ""
	echo ""
done

echo
echo "================================================================="
echo "Build process completed at: $(date)"
echo "================================================================="
