#!/bin/bash

xsd cxx-tree --generate-polymorphic --generate-from-base-ctor --generate-serialization --polymorphic-type CRCLCommand --polymorphic-type-all ../../schemas/CRCLCommandInstance.xsd
if [ $? -ne 0 ] ; then echo "can't build schema" ; fi

xsd cxx-tree --generate-polymorphic --generate-from-base-ctor --generate-serialization --polymorphic-type CRCLCommand --polymorphic-type-all ../../schemas/CRCLCommands.xsd 
if [ $? -ne 0 ] ; then echo "can't build schema" ; fi

xsd cxx-tree --generate-polymorphic --generate-from-base-ctor --generate-serialization  --polymorphic-type-all ../../schemas/CRCLProgramInstance.xsd 
if [ $? -ne 0 ] ; then echo "can't build schema" ; fi

xsd cxx-tree --generate-polymorphic --generate-from-base-ctor --generate-serialization  --polymorphic-type-all ../../schemas/CRCLStatus.xsd 
if [ $? -ne 0 ] ; then echo "can't build schema" ; fi

xsd cxx-tree --generate-polymorphic --generate-from-base-ctor --generate-serialization  --polymorphic-type-all ../../schemas/DataPrimitives.xsd 
if [ $? -ne 0 ] ; then echo "can't build schema" ; fi

xsd cxx-tree ../../schemas/URDF_CRPI.xsd
if [ $? -ne 0 ] ; then echo "can't build schema" ; fi

exit 0
