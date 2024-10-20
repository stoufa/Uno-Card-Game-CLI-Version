#!/bin/bash
# compile the game into a JAR file

mkdir -p out
javac -d out $(find . -name "*.java")
jar cfm uno-game.jar MANIFEST.MF -C out/ .
