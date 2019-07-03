#!/bin/bash

# Copies the contents of the original CRPI repository to a local tree,
# making some changes:
#   - changing path-relative include files and backslashes, e.g.,
#     #include "..\..\Dir\File.h" -> #include "Dir/File.h"

# Run as:
# ./copyit.sh

# the base CRPI repository directory, edit as you see fit
crpi_dir="/usr/local/$USER/github/usnistgov/CRPI"
crpi_devel_branch="$USER"

# check out the devel branch of CRPI
(cd $crpi_dir && git checkout --quiet $crpi_devel_branch && git pull)

# copy and fix the library code
for dir in CRPI Math MotionPrims RegistrationKit Serial ; do
    if ! test -d Libraries/$dir ; then mkdir -p Libraries/$dir ; fi
    for file in `\ls $crpi_dir/Libraries/$dir/*.{c,cpp,h} 2> /dev/null` ; do
	./include-fix.py < $file > Libraries/$dir/`\basename $file`
    done
done

# copy and fix the Sensor library code, a special case with two subdirs
for dir in HRI MoCap ; do
    if ! test -d Libraries/Sensor/$dir ; then mkdir -p Libraries/Sensor/$dir ; fi
    for file in `\ls $crpi_dir/Libraries/Sensor/$dir/*.{c,cpp,h} 2> /dev/null` ; do
	./include-fix.py < $file > Libraries/Sensor/$dir/`\basename $file`
    done
done

# copy the top-level headers into the CRPI library, for consistency
for file in `\ls $crpi_dir/{portable.h,types.h}` ; do
    ./include-fix.py < $file > Libraries/CRPI/`\basename $file`
done

# copy and fix the application code
for dir in Application_CRPI_Test CRPI_Eval XMLInterface ; do
    if ! test -d Applications/$dir ; then mkdir -p Applications/$dir ; fi
    for file in `\ls $crpi_dir/Applications/$dir/*.{c,cpp,h} 2> /dev/null` ; do
	./include-fix.py < $file > Applications/$dir/`\basename $file`
    done
done

# copy and fix the clustering code into the Libraries directory
for dir in Cluster kMeans Patterns ; do
    if ! test -d Libraries/Clustering/$dir ; then mkdir -p Libraries/Clustering/$dir ; fi
    for file in `\ls $crpi_dir/Clustering/$dir/*.{c,cpp,h} 2> /dev/null` ; do
	./include-fix.py < $file > Libraries/Clustering/$dir/`\basename $file`
    done
done

# set up ulapi
if ! test -d Libraries/ulapi ; then (cd Libraries && git clone https://github.com/frederickproctor/ulapi) ; fi

# copy the third-party libraries
if ! test -d Libraries/ThirdParty ; then cp -R $crpi_dir/Libraries/ThirdParty Libraries ; fi

exit 0
