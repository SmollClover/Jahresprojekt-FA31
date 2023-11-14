#!/bin/bash

marp --theme ./MarpTheme/rose-pine-moon.css --bespoke.osc=false --html Tenzur.md
docker run --rm --init -v $PWD:/home/marp/app/ -e LANG=$LANG -e MARP_USER="$(id -u):$(id -g)" marpteam/marp-cli --theme ./MarpTheme/rose-pine-moon.css --pptx Tenzur.md
