
AC_DEFUN([ACX_CRPI_CRCL],
	[AC_MSG_CHECKING([for CRPI-CRCL])]
	[AC_ARG_WITH([crpicrcl],
		[ --with-crpicrcl=<path to CRPI-CRCL>  Specify path to CRPI-CRCLdirectory],
		[dirs=$withval],[dirs="/usr/local /usr/local/crpi_crcl"])]
	for dir in $dirs ; do
		if test -f $dir/Libraries/CRPI/crpi.h ; then CRPI_CRCL_DIR=$dir ; break ; fi
	done
	if test x$CRPI_CRCL_DIR = x ; then
	[AC_MSG_WARN([not found in $dirs, specify using --with-crpicrcl=<path to CRPI-CRCL>])]
	else
	[AC_MSG_RESULT([$CRPI_CRCL_DIR])]
	CRPI_CRCL_CFLAGS="-I$CRPI_CRCL_DIR/include"
	CRPI_CRCL_LIBS="-L$CRPI_CRCL_DIR/lib -lulapi"
dnl put HAVE_CRPI_CRCL in config.h
	[AC_DEFINE(HAVE_CRPI_CRCL,
		1, [Define non-zero if you have CRPI-CRCL.])]
dnl put CRPI_CRCL_DIR in Makefile
	[AC_SUBST(CRPI_CRCL_DIR)]
	[AC_SUBST(CRPI_CRCL_CFLAGS)]
	[AC_SUBST(CRPI_CRCL_LIBS)]
	fi
dnl put CRPI_CRCL_DIR into variable file for use by shell scripts
	echo CRPI_CRCL_DIR=$CRPI_CRCL_DIR > crpi_crcl_dir
dnl enable HAVE_CRPI_CRCL test in Makefile
	[AM_CONDITIONAL(HAVE_CRPI_CRCL, test x$CRPI_CRCL_DIR != x)]
)
