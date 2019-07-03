AC_INIT([crpi], [1.1])
AC_CONFIG_AUX_DIR([config])
AC_CONFIG_SRCDIR([Applications/Application_CRPI_Test/Source.cpp])
AC_CONFIG_HEADERS([config/config.h])
AM_INIT_AUTOMAKE([subdir-objects])

# Checks for programs.
AC_PROG_CC
AC_PROG_CXX
AC_PROG_RANLIB

# Needed to generate config/config.guess, for 'make dist'
AC_CANONICAL_HOST

AC_CHECK_HEADERS([readline/readline.h])
AC_SEARCH_LIBS(tputs, [termcap])
AC_SEARCH_LIBS(readline, [readline])

AC_CHECK_HEADERS([xsd/cxx/config.hxx])
AC_SEARCH_LIBS(stricmp, [xerces-c])

# Configures ulapi.
ACX_ULAPI

# Configures Doxygen.
DX_HTML_FEATURE(ON)
DX_CHM_FEATURE(OFF)
DX_CHI_FEATURE(OFF)
DX_MAN_FEATURE(OFF)
DX_RTF_FEATURE(OFF)
DX_XML_FEATURE(OFF)
DX_PDF_FEATURE(OFF)
DX_PS_FEATURE(OFF)
DX_INIT_DOXYGEN([crpi-crcl],[Doxyfile],[doxygen-doc])

ACX_CRPI_CRCL

AC_CONFIG_FILES([Makefile bin/Makefile lib/Makefile])

AC_OUTPUT
