#!/bin/sh

export crcl_ui_finalname="${project.build.finalName}";
export crcl_ui_fullpath_jar="${project.build.directory}/${project.build.finalName}-jar-with-dependencies.jar";
export crcl_ui_jar="${project.build.finalName}-jar-with-dependencies.jar";
export crcl_ui_fullpath_javadoc_jar="${project.build.directory}/${project.build.finalName}-javadoc.jar";
export crcl_ui_javadoc_jar="${project.build.finalName}-javadoc.jar";
export fullpath_javadoc_jar="${crcl_ui_fullpath_javadoc_jar}";
export javadoc_jar="${crcl_ui_javadoc_jar}";
export fullpath_jar="${crcl_ui_fullpath_jar}";
export jar="${crcl_ui_jar}";


