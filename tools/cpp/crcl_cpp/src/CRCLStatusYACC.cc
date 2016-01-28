/* A Bison parser, made by GNU Bison 3.0.2.  */

/* Bison implementation for Yacc-like parsers in C

   Copyright (C) 1984, 1989-1990, 2000-2013 Free Software Foundation, Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* C LALR(1) parser skeleton written by Richard Stallman, by
   simplifying the original so-called "semantic" parser.  */

/* All symbols defined below should begin with yy or YY, to avoid
   infringing on user name space.  This should be done even for local
   variables, as they might otherwise be expanded by user macros.
   There are some unavoidable exceptions within include files to
   define necessary library symbols; they are noted "INFRINGES ON
   USER NAME SPACE" below.  */

/* Identify Bison output.  */
#define YYBISON 1

/* Bison version.  */
#define YYBISON_VERSION "3.0.2"

/* Skeleton name.  */
#define YYSKELETON_NAME "yacc.c"

/* Pure parsers.  */
#define YYPURE 0

/* Push parsers.  */
#define YYPUSH 0

/* Pull parsers.  */
#define YYPULL 1




/* Copy the first part of user declarations.  */



#include <stdio.h>             // for stderr
#include <string.h>            // for strcat
#include <stdlib.h>            // for malloc, free
#ifdef OWL
#include "owlCRCLStatusClasses.hh"
#else
#include "crcl_cpp/CRCLStatusClasses.hh"
#endif

#define YYERROR_VERBOSE
#define YYDEBUG 1

CRCLStatusFile * CRCLStatusTree; // the parse tree

extern int yylex();
int yyReadData = 0;
int yyReadDataList = 0;

int yyerror(const char * s);




# ifndef YY_NULLPTR
#  if defined __cplusplus && 201103L <= __cplusplus
#   define YY_NULLPTR nullptr
#  else
#   define YY_NULLPTR 0
#  endif
# endif

/* Enabling verbose error messages.  */
#ifdef YYERROR_VERBOSE
# undef YYERROR_VERBOSE
# define YYERROR_VERBOSE 1
#else
# define YYERROR_VERBOSE 0
#endif

/* In a future release of Bison, this section will be replaced
   by #include "CRCLStatusYACC.hh".  */
#ifndef YY_YY_HOME_ISD_PROCTOR_TEST_WS_SRC_CRCL_TOOLS_CPP_CRCL_CPP_SRC_CRCLSTATUSYACC_HH_INCLUDED
# define YY_YY_HOME_ISD_PROCTOR_TEST_WS_SRC_CRCL_TOOLS_CPP_CRCL_CPP_SRC_CRCLSTATUSYACC_HH_INCLUDED
/* Debug traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif
#if YYDEBUG
extern int yydebug;
#endif

/* Token type.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
  enum yytokentype
  {
    BAD = 258,
    DATASTRING = 259,
    ENCODING = 260,
    ENDITEM = 261,
    ENDVERSION = 262,
    SCHEMALOCATION = 263,
    STARTVERSION = 264,
    TERMINALSTRING = 265,
    XMLNSPREFIX = 266,
    XMLNSTARGET = 267,
    XMLVERSION = 268,
    ANGULARVELOCITYEND = 269,
    ANGULARVELOCITYSTART = 270,
    CRCLSTATUSEND = 271,
    CRCLSTATUSSTART = 272,
    COMMANDIDEND = 273,
    COMMANDIDSTART = 274,
    COMMANDSTATEEND = 275,
    COMMANDSTATESTART = 276,
    COMMANDSTATUSEND = 277,
    COMMANDSTATUSSTART = 278,
    FINGER1FORCEEND = 279,
    FINGER1FORCESTART = 280,
    FINGER1POSITIONEND = 281,
    FINGER1POSITIONSTART = 282,
    FINGER2FORCEEND = 283,
    FINGER2FORCESTART = 284,
    FINGER2POSITIONEND = 285,
    FINGER2POSITIONSTART = 286,
    FINGER3FORCEEND = 287,
    FINGER3FORCESTART = 288,
    FINGER3POSITIONEND = 289,
    FINGER3POSITIONSTART = 290,
    FORCEEND = 291,
    FORCESTART = 292,
    GRIPPERNAMEEND = 293,
    GRIPPERNAMESTART = 294,
    GRIPPERSTATUSEND = 295,
    GRIPPERSTATUSSTART = 296,
    IEND = 297,
    ISTART = 298,
    ISPOWEREDEND = 299,
    ISPOWEREDSTART = 300,
    JEND = 301,
    JSTART = 302,
    JOINTNUMBEREND = 303,
    JOINTNUMBERSTART = 304,
    JOINTPOSITIONEND = 305,
    JOINTPOSITIONSTART = 306,
    JOINTSTATUSEND = 307,
    JOINTSTATUSSTART = 308,
    JOINTSTATUSESEND = 309,
    JOINTSTATUSESSTART = 310,
    JOINTTORQUEORFORCEEND = 311,
    JOINTTORQUEORFORCESTART = 312,
    JOINTVELOCITYEND = 313,
    JOINTVELOCITYSTART = 314,
    KEND = 315,
    KSTART = 316,
    LINEARVELOCITYEND = 317,
    LINEARVELOCITYSTART = 318,
    MOMENTEND = 319,
    MOMENTSTART = 320,
    NAMEEND = 321,
    NAMESTART = 322,
    POINTEND = 323,
    POINTSTART = 324,
    POSESTATUSEND = 325,
    POSESTATUSSTART = 326,
    POSEEND = 327,
    POSESTART = 328,
    SEPARATIONEND = 329,
    SEPARATIONSTART = 330,
    STATEDESCRIPTIONEND = 331,
    STATEDESCRIPTIONSTART = 332,
    STATUSIDEND = 333,
    STATUSIDSTART = 334,
    TWISTEND = 335,
    TWISTSTART = 336,
    WRENCHEND = 337,
    WRENCHSTART = 338,
    XAXISEND = 339,
    XAXISSTART = 340,
    XEND = 341,
    XSTART = 342,
    YEND = 343,
    YSTART = 344,
    ZAXISEND = 345,
    ZAXISSTART = 346,
    ZEND = 347,
    ZSTART = 348,
    CRCLSTATUSTYPEDECL = 349,
    COMMANDSTATUSTYPEDECL = 350,
    GRIPPERSTATUSTYPEDECL = 351,
    JOINTSTATUSTYPEDECL = 352,
    JOINTSTATUSESTYPEDECL = 353,
    PARALLELGRIPPERSTATUSTYPEDECL = 354,
    POINTTYPEDECL = 355,
    POSESTATUSTYPEDECL = 356,
    POSETYPEDECL = 357,
    THREEFINGERGRIPPERSTATUSTYPEDECL = 358,
    TWISTTYPEDECL = 359,
    VACUUMGRIPPERSTATUSTYPEDECL = 360,
    VECTORTYPEDECL = 361,
    WRENCHTYPEDECL = 362
  };
#endif

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef union YYSTYPE YYSTYPE;
union YYSTYPE
{


  SchemaLocation *                    SchemaLocationVal;
  XmlHeaderForCRCLStatus *            XmlHeaderForCRCLStatusVal;
  XmlVersion *                        XmlVersionVal;
  int *                               iVal;
  char *                              sVal;
  XmlBoolean *                        XmlBooleanVal;
  XmlDecimal *                        XmlDecimalVal;
  XmlID *                             XmlIDVal;
  XmlNMTOKEN *                        XmlNMTOKENVal;
  XmlNonNegativeInteger *             XmlNonNegativeIntegerVal;
  XmlPositiveInteger *                XmlPositiveIntegerVal;
  XmlString *                         XmlStringVal;

  CRCLStatusFile *                    CRCLStatusFileVal;

  CRCLStatusType *                    CRCLStatusTypeVal;
  CommandStateEnumType *              CommandStateEnumTypeVal;
  CommandStatusType *                 CommandStatusTypeVal;
  FractionType *                      FractionTypeVal;
  GripperStatusType *                 GripperStatusTypeVal;
  JointStatusType *                   JointStatusTypeVal;
  JointStatusesType *                 JointStatusesTypeVal;
  std::list<JointStatusType *> *      ListJointStatusTypeVal;
  ParallelGripperStatusType *         ParallelGripperStatusTypeVal;
  PointType *                         PointTypeVal;
  PoseStatusType *                    PoseStatusTypeVal;
  PoseType *                          PoseTypeVal;
  ThreeFingerGripperStatusType *      ThreeFingerGripperStatusTypeVal;
  TwistType *                         TwistTypeVal;
  VacuumGripperStatusType *           VacuumGripperStatusTypeVal;
  VectorType *                        VectorTypeVal;
  WrenchType *                        WrenchTypeVal;


};
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE yylval;

int yyparse (void);

#endif /* !YY_YY_HOME_ISD_PROCTOR_TEST_WS_SRC_CRCL_TOOLS_CPP_CRCL_CPP_SRC_CRCLSTATUSYACC_HH_INCLUDED  */

/* Copy the second part of user declarations.  */



#ifdef short
# undef short
#endif

#ifdef YYTYPE_UINT8
typedef YYTYPE_UINT8 yytype_uint8;
#else
typedef unsigned char yytype_uint8;
#endif

#ifdef YYTYPE_INT8
typedef YYTYPE_INT8 yytype_int8;
#else
typedef signed char yytype_int8;
#endif

#ifdef YYTYPE_UINT16
typedef YYTYPE_UINT16 yytype_uint16;
#else
typedef unsigned short int yytype_uint16;
#endif

#ifdef YYTYPE_INT16
typedef YYTYPE_INT16 yytype_int16;
#else
typedef short int yytype_int16;
#endif

#ifndef YYSIZE_T
# ifdef __SIZE_TYPE__
#  define YYSIZE_T __SIZE_TYPE__
# elif defined size_t
#  define YYSIZE_T size_t
# elif ! defined YYSIZE_T
#  include <stddef.h> /* INFRINGES ON USER NAME SPACE */
#  define YYSIZE_T size_t
# else
#  define YYSIZE_T unsigned int
# endif
#endif

#define YYSIZE_MAXIMUM ((YYSIZE_T) -1)

#ifndef YY_
# if defined YYENABLE_NLS && YYENABLE_NLS
#  if ENABLE_NLS
#   include <libintl.h> /* INFRINGES ON USER NAME SPACE */
#   define YY_(Msgid) dgettext ("bison-runtime", Msgid)
#  endif
# endif
# ifndef YY_
#  define YY_(Msgid) Msgid
# endif
#endif

#ifndef YY_ATTRIBUTE
# if (defined __GNUC__                                               \
      && (2 < __GNUC__ || (__GNUC__ == 2 && 96 <= __GNUC_MINOR__)))  \
     || defined __SUNPRO_C && 0x5110 <= __SUNPRO_C
#  define YY_ATTRIBUTE(Spec) __attribute__(Spec)
# else
#  define YY_ATTRIBUTE(Spec) /* empty */
# endif
#endif

#ifndef YY_ATTRIBUTE_PURE
# define YY_ATTRIBUTE_PURE   YY_ATTRIBUTE ((__pure__))
#endif

#ifndef YY_ATTRIBUTE_UNUSED
# define YY_ATTRIBUTE_UNUSED YY_ATTRIBUTE ((__unused__))
#endif

#if !defined _Noreturn \
     && (!defined __STDC_VERSION__ || __STDC_VERSION__ < 201112)
# if defined _MSC_VER && 1200 <= _MSC_VER
#  define _Noreturn __declspec (noreturn)
# else
#  define _Noreturn YY_ATTRIBUTE ((__noreturn__))
# endif
#endif

/* Suppress unused-variable warnings by "using" E.  */
#if ! defined lint || defined __GNUC__
# define YYUSE(E) ((void) (E))
#else
# define YYUSE(E) /* empty */
#endif

#if defined __GNUC__ && 407 <= __GNUC__ * 100 + __GNUC_MINOR__
/* Suppress an incorrect diagnostic about yylval being uninitialized.  */
# define YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN \
    _Pragma ("GCC diagnostic push") \
    _Pragma ("GCC diagnostic ignored \"-Wuninitialized\"")\
    _Pragma ("GCC diagnostic ignored \"-Wmaybe-uninitialized\"")
# define YY_IGNORE_MAYBE_UNINITIALIZED_END \
    _Pragma ("GCC diagnostic pop")
#else
# define YY_INITIAL_VALUE(Value) Value
#endif
#ifndef YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
# define YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
# define YY_IGNORE_MAYBE_UNINITIALIZED_END
#endif
#ifndef YY_INITIAL_VALUE
# define YY_INITIAL_VALUE(Value) /* Nothing. */
#endif


#if ! defined yyoverflow || YYERROR_VERBOSE

/* The parser invokes alloca or malloc; define the necessary symbols.  */

# ifdef YYSTACK_USE_ALLOCA
#  if YYSTACK_USE_ALLOCA
#   ifdef __GNUC__
#    define YYSTACK_ALLOC __builtin_alloca
#   elif defined __BUILTIN_VA_ARG_INCR
#    include <alloca.h> /* INFRINGES ON USER NAME SPACE */
#   elif defined _AIX
#    define YYSTACK_ALLOC __alloca
#   elif defined _MSC_VER
#    include <malloc.h> /* INFRINGES ON USER NAME SPACE */
#    define alloca _alloca
#   else
#    define YYSTACK_ALLOC alloca
#    if ! defined _ALLOCA_H && ! defined EXIT_SUCCESS
#     include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
      /* Use EXIT_SUCCESS as a witness for stdlib.h.  */
#     ifndef EXIT_SUCCESS
#      define EXIT_SUCCESS 0
#     endif
#    endif
#   endif
#  endif
# endif

# ifdef YYSTACK_ALLOC
   /* Pacify GCC's 'empty if-body' warning.  */
#  define YYSTACK_FREE(Ptr) do { /* empty */; } while (0)
#  ifndef YYSTACK_ALLOC_MAXIMUM
    /* The OS might guarantee only one guard page at the bottom of the stack,
       and a page size can be as small as 4096 bytes.  So we cannot safely
       invoke alloca (N) if N exceeds 4096.  Use a slightly smaller number
       to allow for a few compiler-allocated temporary stack slots.  */
#   define YYSTACK_ALLOC_MAXIMUM 4032 /* reasonable circa 2006 */
#  endif
# else
#  define YYSTACK_ALLOC YYMALLOC
#  define YYSTACK_FREE YYFREE
#  ifndef YYSTACK_ALLOC_MAXIMUM
#   define YYSTACK_ALLOC_MAXIMUM YYSIZE_MAXIMUM
#  endif
#  if (defined __cplusplus && ! defined EXIT_SUCCESS \
       && ! ((defined YYMALLOC || defined malloc) \
             && (defined YYFREE || defined free)))
#   include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#   ifndef EXIT_SUCCESS
#    define EXIT_SUCCESS 0
#   endif
#  endif
#  ifndef YYMALLOC
#   define YYMALLOC malloc
#   if ! defined malloc && ! defined EXIT_SUCCESS
void *malloc (YYSIZE_T); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
#  ifndef YYFREE
#   define YYFREE free
#   if ! defined free && ! defined EXIT_SUCCESS
void free (void *); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
# endif
#endif /* ! defined yyoverflow || YYERROR_VERBOSE */


#if (! defined yyoverflow \
     && (! defined __cplusplus \
         || (defined YYSTYPE_IS_TRIVIAL && YYSTYPE_IS_TRIVIAL)))

/* A type that is properly aligned for any stack member.  */
union yyalloc
{
  yytype_int16 yyss_alloc;
  YYSTYPE yyvs_alloc;
};

/* The size of the maximum gap between one aligned stack and the next.  */
# define YYSTACK_GAP_MAXIMUM (sizeof (union yyalloc) - 1)

/* The size of an array large to enough to hold all stacks, each with
   N elements.  */
# define YYSTACK_BYTES(N) \
     ((N) * (sizeof (yytype_int16) + sizeof (YYSTYPE)) \
      + YYSTACK_GAP_MAXIMUM)

# define YYCOPY_NEEDED 1

/* Relocate STACK from its old location to the new one.  The
   local variables YYSIZE and YYSTACKSIZE give the old and new number of
   elements in the stack, and YYPTR gives the new location of the
   stack.  Advance YYPTR to a properly aligned location for the next
   stack.  */
# define YYSTACK_RELOCATE(Stack_alloc, Stack)                           \
    do                                                                  \
      {                                                                 \
        YYSIZE_T yynewbytes;                                            \
        YYCOPY (&yyptr->Stack_alloc, Stack, yysize);                    \
        Stack = &yyptr->Stack_alloc;                                    \
        yynewbytes = yystacksize * sizeof (*Stack) + YYSTACK_GAP_MAXIMUM; \
        yyptr += yynewbytes / sizeof (*yyptr);                          \
      }                                                                 \
    while (0)

#endif

#if defined YYCOPY_NEEDED && YYCOPY_NEEDED
/* Copy COUNT objects from SRC to DST.  The source and destination do
   not overlap.  */
# ifndef YYCOPY
#  if defined __GNUC__ && 1 < __GNUC__
#   define YYCOPY(Dst, Src, Count) \
      __builtin_memcpy (Dst, Src, (Count) * sizeof (*(Src)))
#  else
#   define YYCOPY(Dst, Src, Count)              \
      do                                        \
        {                                       \
          YYSIZE_T yyi;                         \
          for (yyi = 0; yyi < (Count); yyi++)   \
            (Dst)[yyi] = (Src)[yyi];            \
        }                                       \
      while (0)
#  endif
# endif
#endif /* !YYCOPY_NEEDED */

/* YYFINAL -- State number of the termination state.  */
#define YYFINAL  5
/* YYLAST -- Last index in YYTABLE.  */
#define YYLAST   186

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  108
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  90
/* YYNRULES -- Number of rules.  */
#define YYNRULES  110
/* YYNSTATES -- Number of states.  */
#define YYNSTATES  265

/* YYTRANSLATE[YYX] -- Symbol number corresponding to YYX as returned
   by yylex, with out-of-bounds checking.  */
#define YYUNDEFTOK  2
#define YYMAXUTOK   362

#define YYTRANSLATE(YYX)                                                \
  ((unsigned int) (YYX) <= YYMAXUTOK ? yytranslate[YYX] : YYUNDEFTOK)

/* YYTRANSLATE[TOKEN-NUM] -- Symbol number corresponding to TOKEN-NUM
   as returned by yylex, without out-of-bounds checking.  */
static const yytype_uint8 yytranslate[] =
{
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    29,    30,    31,    32,    33,    34,
      35,    36,    37,    38,    39,    40,    41,    42,    43,    44,
      45,    46,    47,    48,    49,    50,    51,    52,    53,    54,
      55,    56,    57,    58,    59,    60,    61,    62,    63,    64,
      65,    66,    67,    68,    69,    70,    71,    72,    73,    74,
      75,    76,    77,    78,    79,    80,    81,    82,    83,    84,
      85,    86,    87,    88,    89,    90,    91,    92,    93,    94,
      95,    96,    97,    98,    99,   100,   101,   102,   103,   104,
     105,   106,   107
};

#if YYDEBUG
  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
static const yytype_uint16 yyrline[] =
{
       0,   239,   239,   249,   254,   261,   270,   279,   288,   297,
     306,   315,   324,   330,   343,   348,   355,   355,   361,   361,
     371,   378,   384,   385,   385,   392,   393,   393,   404,   405,
     405,   412,   413,   413,   424,   425,   425,   432,   433,   433,
     443,   448,   448,   454,   456,   458,   464,   465,   470,   470,
     475,   475,   481,   481,   486,   486,   493,   494,   494,   500,
     507,   512,   518,   519,   525,   526,   526,   533,   534,   534,
     540,   540,   545,   550,   553,   560,   566,   567,   567,   572,
     578,   583,   590,   591,   596,   602,   607,   607,   614,   615,
     615,   621,   621,   627,   634,   635,   640,   646,   652,   653,
     658,   663,   663,   668,   668,   673,   678,   678,   683,   691,
     702
};
#endif

#if YYDEBUG || YYERROR_VERBOSE || 0
/* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
   First, the terminals, then, starting at YYNTOKENS, nonterminals.  */
static const char *const yytname[] =
{
  "$end", "error", "$undefined", "BAD", "DATASTRING", "ENCODING",
  "ENDITEM", "ENDVERSION", "SCHEMALOCATION", "STARTVERSION",
  "TERMINALSTRING", "XMLNSPREFIX", "XMLNSTARGET", "XMLVERSION",
  "ANGULARVELOCITYEND", "ANGULARVELOCITYSTART", "CRCLSTATUSEND",
  "CRCLSTATUSSTART", "COMMANDIDEND", "COMMANDIDSTART", "COMMANDSTATEEND",
  "COMMANDSTATESTART", "COMMANDSTATUSEND", "COMMANDSTATUSSTART",
  "FINGER1FORCEEND", "FINGER1FORCESTART", "FINGER1POSITIONEND",
  "FINGER1POSITIONSTART", "FINGER2FORCEEND", "FINGER2FORCESTART",
  "FINGER2POSITIONEND", "FINGER2POSITIONSTART", "FINGER3FORCEEND",
  "FINGER3FORCESTART", "FINGER3POSITIONEND", "FINGER3POSITIONSTART",
  "FORCEEND", "FORCESTART", "GRIPPERNAMEEND", "GRIPPERNAMESTART",
  "GRIPPERSTATUSEND", "GRIPPERSTATUSSTART", "IEND", "ISTART",
  "ISPOWEREDEND", "ISPOWEREDSTART", "JEND", "JSTART", "JOINTNUMBEREND",
  "JOINTNUMBERSTART", "JOINTPOSITIONEND", "JOINTPOSITIONSTART",
  "JOINTSTATUSEND", "JOINTSTATUSSTART", "JOINTSTATUSESEND",
  "JOINTSTATUSESSTART", "JOINTTORQUEORFORCEEND", "JOINTTORQUEORFORCESTART",
  "JOINTVELOCITYEND", "JOINTVELOCITYSTART", "KEND", "KSTART",
  "LINEARVELOCITYEND", "LINEARVELOCITYSTART", "MOMENTEND", "MOMENTSTART",
  "NAMEEND", "NAMESTART", "POINTEND", "POINTSTART", "POSESTATUSEND",
  "POSESTATUSSTART", "POSEEND", "POSESTART", "SEPARATIONEND",
  "SEPARATIONSTART", "STATEDESCRIPTIONEND", "STATEDESCRIPTIONSTART",
  "STATUSIDEND", "STATUSIDSTART", "TWISTEND", "TWISTSTART", "WRENCHEND",
  "WRENCHSTART", "XAXISEND", "XAXISSTART", "XEND", "XSTART", "YEND",
  "YSTART", "ZAXISEND", "ZAXISSTART", "ZEND", "ZSTART",
  "CRCLSTATUSTYPEDECL", "COMMANDSTATUSTYPEDECL", "GRIPPERSTATUSTYPEDECL",
  "JOINTSTATUSTYPEDECL", "JOINTSTATUSESTYPEDECL",
  "PARALLELGRIPPERSTATUSTYPEDECL", "POINTTYPEDECL", "POSESTATUSTYPEDECL",
  "POSETYPEDECL", "THREEFINGERGRIPPERSTATUSTYPEDECL", "TWISTTYPEDECL",
  "VACUUMGRIPPERSTATUSTYPEDECL", "VECTORTYPEDECL", "WRENCHTYPEDECL",
  "$accept", "y_CRCLStatusFile", "y_XmlHeaderForCRCLStatus",
  "y_SchemaLocation", "y_XmlBoolean", "y_XmlDecimal", "y_XmlID",
  "y_XmlNMTOKEN", "y_XmlNonNegativeInteger", "y_XmlPositiveInteger",
  "y_XmlString", "y_XmlVersion", "y_AngularVelocity_VectorType",
  "y_CRCLStatusType", "y_CommandID_XmlNonNegativeInteger", "$@1",
  "y_CommandState_CommandStateEnumType", "$@2", "y_CommandStatusType",
  "y_CommandStatus_CommandStatusType", "y_Finger1Force_XmlDecimal_0",
  "$@3", "y_Finger1Position_FractionType_0", "$@4",
  "y_Finger2Force_XmlDecimal_0", "$@5", "y_Finger2Position_FractionType_0",
  "$@6", "y_Finger3Force_XmlDecimal_0", "$@7",
  "y_Finger3Position_FractionType_0", "$@8", "y_Force_VectorType",
  "y_GripperName_XmlNMTOKEN", "$@9", "y_GripperStatusTypeAny",
  "y_GripperStatus_GripperStatusType_0", "y_I_XmlDecimal", "$@10",
  "y_IsPowered_XmlBoolean", "$@11", "y_J_XmlDecimal", "$@12",
  "y_JointNumber_XmlPositiveInteger", "$@13",
  "y_JointPosition_XmlDecimal_0", "$@14", "y_JointStatusType",
  "y_JointStatus_JointStatusType_1_u", "y_JointStatusesType",
  "y_JointStatuses_JointStatusesType_0",
  "y_JointTorqueOrForce_XmlDecimal_0", "$@15",
  "y_JointVelocity_XmlDecimal_0", "$@16", "y_K_XmlDecimal", "$@17",
  "y_LinearVelocity_VectorType", "y_ListJointStatus_JointStatusType_1_u",
  "y_Moment_VectorType", "y_Name_XmlID_0", "$@18", "y_PointType",
  "y_Point_PointType", "y_PoseStatusType", "y_PoseStatus_PoseStatusType_0",
  "y_PoseType", "y_Pose_PoseType", "y_Separation_XmlDecimal", "$@19",
  "y_StateDescription_XmlString_0", "$@20",
  "y_StatusID_XmlPositiveInteger", "$@21", "y_TwistType",
  "y_Twist_TwistType_0", "y_VectorType", "y_WrenchType",
  "y_Wrench_WrenchType_0", "y_XAxis_VectorType", "y_X_XmlDecimal", "$@22",
  "y_Y_XmlDecimal", "$@23", "y_ZAxis_VectorType", "y_Z_XmlDecimal", "$@24",
  "y_x_ParallelGripperStatusType", "y_x_ThreeFingerGripperStatusType",
  "y_x_VacuumGripperStatusType", YY_NULLPTR
};
#endif

# ifdef YYPRINT
/* YYTOKNUM[NUM] -- (External) token number corresponding to the
   (internal) symbol number NUM (which must be that of a token).  */
static const yytype_uint16 yytoknum[] =
{
       0,   256,   257,   258,   259,   260,   261,   262,   263,   264,
     265,   266,   267,   268,   269,   270,   271,   272,   273,   274,
     275,   276,   277,   278,   279,   280,   281,   282,   283,   284,
     285,   286,   287,   288,   289,   290,   291,   292,   293,   294,
     295,   296,   297,   298,   299,   300,   301,   302,   303,   304,
     305,   306,   307,   308,   309,   310,   311,   312,   313,   314,
     315,   316,   317,   318,   319,   320,   321,   322,   323,   324,
     325,   326,   327,   328,   329,   330,   331,   332,   333,   334,
     335,   336,   337,   338,   339,   340,   341,   342,   343,   344,
     345,   346,   347,   348,   349,   350,   351,   352,   353,   354,
     355,   356,   357,   358,   359,   360,   361,   362
};
# endif

#define YYPACT_NINF -166

#define yypact_value_is_default(Yystate) \
  (!!((Yystate) == (-166)))

#define YYTABLE_NINF -1

#define yytable_value_is_error(Yytable_value) \
  0

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
     STATE-NUM.  */
static const yytype_int16 yypact[] =
{
      -4,     0,    14,    -2,     6,  -166,     7,    -1,    11,    15,
      10,  -166,    13,  -166,   -43,    12,    19,  -166,    23,     8,
    -166,  -166,  -166,    24,   -23,    30,   -43,    16,    29,   -35,
    -166,   -29,    20,  -166,   -43,   -13,    36,     2,  -166,    38,
     -31,     1,  -166,   -43,   -21,   -96,  -166,  -166,    45,    31,
      49,  -166,     1,   -17,  -166,    52,    53,    54,    21,  -166,
    -166,  -166,    58,  -166,    59,   -11,   -43,    18,  -166,    61,
      -9,   -43,   -43,   -43,  -166,  -166,    46,    64,  -166,    68,
    -166,    26,  -166,   -43,     4,    72,    -3,    40,    40,    40,
    -166,  -166,    17,    78,  -166,    79,    33,    25,  -166,   -43,
       9,    80,  -166,    82,    22,    63,    48,  -166,    76,    94,
    -166,    93,    43,    95,    27,    39,  -166,   -43,    28,  -166,
      97,  -166,    98,    74,   100,  -166,  -166,  -166,    32,    64,
    -166,   103,    55,   -43,    47,   105,    34,   105,   101,    81,
    -166,   109,  -166,  -166,   111,    84,  -166,  -166,    73,   116,
    -166,   117,  -166,    35,  -166,   -43,    42,   105,  -166,    62,
     105,  -166,   105,    65,  -166,    89,   116,   124,  -166,   125,
     107,   129,  -166,  -166,    85,   116,  -166,   128,    50,    99,
    -166,    51,  -166,   122,   102,   105,  -166,  -166,    66,   118,
     133,  -166,   137,   119,  -166,   106,  -166,    90,   116,  -166,
     139,    56,   141,   104,  -166,  -166,  -166,    88,  -166,  -166,
     123,   150,  -166,   149,   126,  -166,  -166,   108,   116,  -166,
     151,  -166,  -166,   152,   110,  -166,  -166,   127,   116,  -166,
     154,  -166,  -166,    70,   116,  -166,   116,  -166,   156,  -166,
    -166,   140,   116,  -166,  -166,    75,   116,   130,   116,  -166,
    -166,   142,   116,  -166,    77,  -166,   121,   116,  -166,   136,
    -166,  -166,   113,  -166,  -166
};

  /* YYDEFACT[STATE-NUM] -- Default reduction number in state STATE-NUM.
     Performed when YYTABLE does not specify something else to do.  Zero
     means the default is an error.  */
static const yytype_uint8 yydefact[] =
{
       0,     0,     0,     0,     0,     1,     0,     0,     0,     0,
       0,    12,     0,     3,    76,     0,     0,     4,     0,     0,
       2,    13,    77,     0,    62,     0,    76,     0,     0,    82,
       7,     0,     0,    21,    76,     0,     0,    46,    78,     0,
       0,     0,    63,    76,     0,     0,    15,    16,     0,     0,
       0,    73,    61,     0,    83,     0,     0,     0,     0,    43,
      44,    45,     0,    91,     0,    88,    76,     0,    74,     0,
      94,    76,    76,    76,    47,     9,     0,     0,    18,     0,
      20,     0,    60,    76,     0,     0,    98,     0,     0,     0,
      17,    10,     0,     0,    89,     0,    56,     0,    85,    76,
       0,     0,    81,     0,     0,    25,     0,    92,     0,     0,
      54,     0,    64,     0,     0,     0,    95,    76,     0,    41,
       0,   108,     0,    31,     0,   110,    19,    11,     0,     0,
      57,     0,    67,    76,     0,     0,     0,     0,     0,     0,
      99,     0,    86,    26,     0,    37,    50,    90,     0,     0,
      65,     0,    59,     0,    80,    76,     0,     0,    84,     0,
       0,    93,     0,     0,     8,     0,     0,     0,    32,     0,
      22,     0,    55,     6,     0,     0,    68,     0,     0,     0,
     100,     0,    72,     0,     0,     0,    97,    42,     0,     0,
       0,    38,     0,    28,     5,     0,    58,     0,     0,   101,
       0,     0,     0,     0,   105,    14,    40,     0,    87,    27,
       0,     0,    23,     0,    34,    51,    66,     0,     0,   103,
       0,    79,    48,     0,     0,    75,    33,     0,     0,    29,
       0,   109,    69,     0,     0,   106,     0,    52,     0,    96,
      39,     0,     0,    35,   102,     0,     0,     0,     0,    70,
      24,     0,     0,   104,     0,    49,     0,     0,    30,     0,
     107,    53,     0,    36,    71
};

  /* YYPGOTO[NTERM-NUM].  */
static const yytype_int16 yypgoto[] =
{
    -166,  -166,  -166,  -166,  -166,  -165,  -166,  -166,  -166,    57,
    -166,  -166,  -166,  -166,  -166,  -166,  -166,  -166,  -166,  -166,
    -166,  -166,  -166,  -166,  -166,  -166,  -166,  -166,  -166,  -166,
    -166,  -166,  -166,   -77,  -166,  -166,  -166,  -166,  -166,  -166,
    -166,  -166,  -166,  -166,  -166,  -166,  -166,  -166,   131,  -166,
    -166,  -166,  -166,  -166,  -166,  -166,  -166,  -166,  -166,  -166,
     -26,  -166,  -166,  -166,  -166,  -166,  -166,  -166,  -166,  -166,
    -166,  -166,  -166,  -166,  -166,  -166,  -135,  -166,  -166,  -166,
    -166,  -166,  -166,  -166,  -166,  -166,  -166,  -166,  -166,  -166
};

  /* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int16 yydefgoto[] =
{
      -1,     2,     9,    13,   195,   174,    31,   165,    76,    92,
     128,     3,   161,    15,    40,    62,    65,    93,    27,    24,
     193,   228,   123,   167,   214,   242,   145,   190,   231,   252,
     170,   211,   163,   104,   141,    58,    46,   203,   236,   125,
     171,   224,   248,    96,   129,   112,   149,    67,    51,    35,
      29,   132,   175,   152,   198,   239,   257,   138,    52,   186,
      19,    25,   134,   114,    44,    37,    84,    70,   121,   166,
      80,   109,    49,    77,   100,    86,   156,   118,   102,   136,
     178,   218,   201,   234,   158,   221,   246,    59,    60,    61
};

  /* YYTABLE[YYPACT[STATE-NUM]] -- What to do in state STATE-NUM.  If
     positive, shift that token.  If negative, reduce the rule whose
     number is the opposite.  If YYTABLE_NINF, syntax error.  */
static const yytype_uint16 yytable[] =
{
      32,   188,   159,    55,    10,     1,    11,    56,    41,    57,
     197,   105,   106,     4,     5,     6,     7,    53,     8,    12,
      16,    14,   181,    17,    18,   183,    21,   184,    20,    22,
      26,    23,    28,   217,    30,    34,    36,    38,    33,    39,
      81,    42,    43,    45,    47,    87,    88,    89,    48,    54,
     207,    63,    64,   233,    50,    66,    69,    97,    71,    72,
      73,    74,    75,   241,    90,    78,    79,    83,    91,   245,
      82,   247,    85,   115,    94,    95,    98,   251,    99,   103,
     101,   254,   108,   256,   111,   110,   117,   259,   119,   116,
     122,   139,   262,   124,   113,   107,   126,   120,   127,   130,
     131,   133,   137,   142,   143,   144,   146,   153,   147,   150,
     140,   155,   135,   164,   151,   154,   160,   168,   162,   169,
     173,   172,   177,   176,   182,   157,   180,   187,   189,   179,
     185,   191,   192,   194,   199,   196,   205,   210,   206,   200,
     208,   204,   202,   212,   209,   219,   216,   222,   213,   220,
     215,   223,   225,   226,   227,   229,   244,   235,   237,   230,
     243,   240,   249,   253,   250,     0,   232,   261,   263,   260,
     258,   238,   255,   264,     0,     0,     0,     0,     0,     0,
       0,     0,     0,    68,     0,     0,   148
};

static const yytype_int16 yycheck[] =
{
      26,   166,   137,    99,     5,     9,     7,   103,    34,   105,
     175,    88,    89,    13,     0,    17,    10,    43,    11,     8,
      10,     6,   157,    10,    67,   160,     7,   162,    16,     6,
       6,    23,    55,   198,     4,     6,    71,    66,    22,    19,
      66,    54,     6,    41,     6,    71,    72,    73,    79,    70,
     185,     6,    21,   218,    53,     6,    73,    83,     6,     6,
       6,    40,     4,   228,    18,     6,    77,     6,     4,   234,
      52,   236,    81,    99,     6,    49,    72,   242,     6,    39,
      83,   246,     4,   248,    51,     6,     6,   252,     6,    80,
      27,   117,   257,    45,    69,    78,    20,    75,     4,     6,
      57,     6,    63,     6,     6,    31,     6,   133,    76,     6,
      82,     6,    85,     4,    59,    68,    15,     6,    37,    35,
       4,    48,    87,     6,    62,    91,    84,    38,     4,   155,
      65,     6,    25,     4,     6,    50,    14,     4,    36,    89,
      74,    90,    43,     6,    26,     6,    56,     6,    29,    93,
      44,    47,    64,    30,     4,     6,    86,     6,     6,    33,
       6,    34,     6,    88,    24,    -1,    58,    46,    32,    92,
      28,    61,    42,    60,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    52,    -1,    -1,   129
};

  /* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
     symbol of state STATE-NUM.  */
static const yytype_uint8 yystos[] =
{
       0,     9,   109,   119,    13,     0,    17,    10,    11,   110,
       5,     7,     8,   111,     6,   121,    10,    10,    67,   168,
      16,     7,     6,    23,   127,   169,     6,   126,    55,   158,
       4,   114,   168,    22,     6,   157,    71,   173,    66,    19,
     122,   168,    54,     6,   172,    41,   144,     6,    79,   180,
      53,   156,   166,   168,    70,    99,   103,   105,   143,   195,
     196,   197,   123,     6,    21,   124,     6,   155,   156,    73,
     175,     6,     6,     6,    40,     4,   116,   181,     6,    77,
     178,   168,    52,     6,   174,    81,   183,   168,   168,   168,
      18,     4,   117,   125,     6,    49,   151,   168,    72,     6,
     182,    83,   186,    39,   141,   141,   141,    78,     4,   179,
       6,    51,   153,    69,   171,   168,    80,     6,   185,     6,
      75,   176,    27,   130,    45,   147,    20,     4,   118,   152,
       6,    57,   159,     6,   170,    85,   187,    63,   165,   168,
      82,   142,     6,     6,    31,   134,     6,    76,   117,   154,
       6,    59,   161,   168,    68,     6,   184,    91,   192,   184,
      15,   120,    37,   140,     4,   115,   177,   131,     6,    35,
     138,   148,    48,     4,   113,   160,     6,    87,   188,   168,
      84,   184,    62,   184,   184,    65,   167,    38,   113,     4,
     135,     6,    25,   128,     4,   112,    50,   113,   162,     6,
      89,   190,    43,   145,    90,    14,    36,   184,    74,    26,
       4,   139,     6,    29,   132,    44,    56,   113,   189,     6,
      93,   193,     6,    47,   149,    64,    30,     4,   129,     6,
      33,   136,    58,   113,   191,     6,   146,     6,    61,   163,
      34,   113,   133,     6,    86,   113,   194,   113,   150,     6,
      24,   113,   137,    88,   113,    42,   113,   164,    28,   113,
      92,    46,   113,    32,    60
};

  /* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_uint8 yyr1[] =
{
       0,   108,   109,   110,   111,   112,   113,   114,   115,   116,
     117,   118,   119,   119,   120,   121,   123,   122,   125,   124,
     126,   127,   128,   129,   128,   130,   131,   130,   132,   133,
     132,   134,   135,   134,   136,   137,   136,   138,   139,   138,
     140,   142,   141,   143,   143,   143,   144,   144,   146,   145,
     148,   147,   150,   149,   152,   151,   153,   154,   153,   155,
     156,   157,   158,   158,   159,   160,   159,   161,   162,   161,
     164,   163,   165,   166,   166,   167,   168,   169,   168,   170,
     171,   172,   173,   173,   174,   175,   177,   176,   178,   179,
     178,   181,   180,   182,   183,   183,   184,   185,   186,   186,
     187,   189,   188,   191,   190,   192,   194,   193,   195,   196,
     197
};

  /* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
static const yytype_uint8 yyr2[] =
{
       0,     2,     5,     2,     2,     1,     1,     1,     1,     1,
       1,     1,     4,     6,     3,     6,     0,     5,     0,     5,
       6,     3,     0,     0,     5,     0,     0,     5,     0,     0,
       5,     0,     0,     5,     0,     0,     5,     0,     0,     5,
       3,     0,     5,     1,     1,     1,     0,     3,     0,     5,
       0,     5,     0,     5,     0,     5,     0,     0,     5,     6,
       3,     3,     0,     3,     0,     0,     5,     0,     0,     5,
       0,     5,     3,     1,     2,     3,     0,     0,     5,     5,
       3,     5,     0,     3,     5,     3,     0,     5,     0,     0,
       5,     0,     5,     4,     0,     3,     5,     4,     0,     3,
       3,     0,     5,     0,     5,     3,     0,     5,     5,    10,
       5
};


#define yyerrok         (yyerrstatus = 0)
#define yyclearin       (yychar = YYEMPTY)
#define YYEMPTY         (-2)
#define YYEOF           0

#define YYACCEPT        goto yyacceptlab
#define YYABORT         goto yyabortlab
#define YYERROR         goto yyerrorlab


#define YYRECOVERING()  (!!yyerrstatus)

#define YYBACKUP(Token, Value)                                  \
do                                                              \
  if (yychar == YYEMPTY)                                        \
    {                                                           \
      yychar = (Token);                                         \
      yylval = (Value);                                         \
      YYPOPSTACK (yylen);                                       \
      yystate = *yyssp;                                         \
      goto yybackup;                                            \
    }                                                           \
  else                                                          \
    {                                                           \
      yyerror (YY_("syntax error: cannot back up")); \
      YYERROR;                                                  \
    }                                                           \
while (0)

/* Error token number */
#define YYTERROR        1
#define YYERRCODE       256



/* Enable debugging if requested.  */
#if YYDEBUG

# ifndef YYFPRINTF
#  include <stdio.h> /* INFRINGES ON USER NAME SPACE */
#  define YYFPRINTF fprintf
# endif

# define YYDPRINTF(Args)                        \
do {                                            \
  if (yydebug)                                  \
    YYFPRINTF Args;                             \
} while (0)

/* This macro is provided for backward compatibility. */
#ifndef YY_LOCATION_PRINT
# define YY_LOCATION_PRINT(File, Loc) ((void) 0)
#endif


# define YY_SYMBOL_PRINT(Title, Type, Value, Location)                    \
do {                                                                      \
  if (yydebug)                                                            \
    {                                                                     \
      YYFPRINTF (stderr, "%s ", Title);                                   \
      yy_symbol_print (stderr,                                            \
                  Type, Value); \
      YYFPRINTF (stderr, "\n");                                           \
    }                                                                     \
} while (0)


/*----------------------------------------.
| Print this symbol's value on YYOUTPUT.  |
`----------------------------------------*/

static void
yy_symbol_value_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep)
{
  FILE *yyo = yyoutput;
  YYUSE (yyo);
  if (!yyvaluep)
    return;
# ifdef YYPRINT
  if (yytype < YYNTOKENS)
    YYPRINT (yyoutput, yytoknum[yytype], *yyvaluep);
# endif
  YYUSE (yytype);
}


/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

static void
yy_symbol_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep)
{
  YYFPRINTF (yyoutput, "%s %s (",
             yytype < YYNTOKENS ? "token" : "nterm", yytname[yytype]);

  yy_symbol_value_print (yyoutput, yytype, yyvaluep);
  YYFPRINTF (yyoutput, ")");
}

/*------------------------------------------------------------------.
| yy_stack_print -- Print the state stack from its BOTTOM up to its |
| TOP (included).                                                   |
`------------------------------------------------------------------*/

static void
yy_stack_print (yytype_int16 *yybottom, yytype_int16 *yytop)
{
  YYFPRINTF (stderr, "Stack now");
  for (; yybottom <= yytop; yybottom++)
    {
      int yybot = *yybottom;
      YYFPRINTF (stderr, " %d", yybot);
    }
  YYFPRINTF (stderr, "\n");
}

# define YY_STACK_PRINT(Bottom, Top)                            \
do {                                                            \
  if (yydebug)                                                  \
    yy_stack_print ((Bottom), (Top));                           \
} while (0)


/*------------------------------------------------.
| Report that the YYRULE is going to be reduced.  |
`------------------------------------------------*/

static void
yy_reduce_print (yytype_int16 *yyssp, YYSTYPE *yyvsp, int yyrule)
{
  unsigned long int yylno = yyrline[yyrule];
  int yynrhs = yyr2[yyrule];
  int yyi;
  YYFPRINTF (stderr, "Reducing stack by rule %d (line %lu):\n",
             yyrule - 1, yylno);
  /* The symbols being reduced.  */
  for (yyi = 0; yyi < yynrhs; yyi++)
    {
      YYFPRINTF (stderr, "   $%d = ", yyi + 1);
      yy_symbol_print (stderr,
                       yystos[yyssp[yyi + 1 - yynrhs]],
                       &(yyvsp[(yyi + 1) - (yynrhs)])
                                              );
      YYFPRINTF (stderr, "\n");
    }
}

# define YY_REDUCE_PRINT(Rule)          \
do {                                    \
  if (yydebug)                          \
    yy_reduce_print (yyssp, yyvsp, Rule); \
} while (0)

/* Nonzero means print parse trace.  It is left uninitialized so that
   multiple parsers can coexist.  */
int yydebug;
#else /* !YYDEBUG */
# define YYDPRINTF(Args)
# define YY_SYMBOL_PRINT(Title, Type, Value, Location)
# define YY_STACK_PRINT(Bottom, Top)
# define YY_REDUCE_PRINT(Rule)
#endif /* !YYDEBUG */


/* YYINITDEPTH -- initial size of the parser's stacks.  */
#ifndef YYINITDEPTH
# define YYINITDEPTH 200
#endif

/* YYMAXDEPTH -- maximum size the stacks can grow to (effective only
   if the built-in stack extension method is used).

   Do not make this value too large; the results are undefined if
   YYSTACK_ALLOC_MAXIMUM < YYSTACK_BYTES (YYMAXDEPTH)
   evaluated with infinite-precision integer arithmetic.  */

#ifndef YYMAXDEPTH
# define YYMAXDEPTH 10000
#endif


#if YYERROR_VERBOSE

# ifndef yystrlen
#  if defined __GLIBC__ && defined _STRING_H
#   define yystrlen strlen
#  else
/* Return the length of YYSTR.  */
static YYSIZE_T
yystrlen (const char *yystr)
{
  YYSIZE_T yylen;
  for (yylen = 0; yystr[yylen]; yylen++)
    continue;
  return yylen;
}
#  endif
# endif

# ifndef yystpcpy
#  if defined __GLIBC__ && defined _STRING_H && defined _GNU_SOURCE
#   define yystpcpy stpcpy
#  else
/* Copy YYSRC to YYDEST, returning the address of the terminating '\0' in
   YYDEST.  */
static char *
yystpcpy (char *yydest, const char *yysrc)
{
  char *yyd = yydest;
  const char *yys = yysrc;

  while ((*yyd++ = *yys++) != '\0')
    continue;

  return yyd - 1;
}
#  endif
# endif

# ifndef yytnamerr
/* Copy to YYRES the contents of YYSTR after stripping away unnecessary
   quotes and backslashes, so that it's suitable for yyerror.  The
   heuristic is that double-quoting is unnecessary unless the string
   contains an apostrophe, a comma, or backslash (other than
   backslash-backslash).  YYSTR is taken from yytname.  If YYRES is
   null, do not copy; instead, return the length of what the result
   would have been.  */
static YYSIZE_T
yytnamerr (char *yyres, const char *yystr)
{
  if (*yystr == '"')
    {
      YYSIZE_T yyn = 0;
      char const *yyp = yystr;

      for (;;)
        switch (*++yyp)
          {
          case '\'':
          case ',':
            goto do_not_strip_quotes;

          case '\\':
            if (*++yyp != '\\')
              goto do_not_strip_quotes;
            /* Fall through.  */
          default:
            if (yyres)
              yyres[yyn] = *yyp;
            yyn++;
            break;

          case '"':
            if (yyres)
              yyres[yyn] = '\0';
            return yyn;
          }
    do_not_strip_quotes: ;
    }

  if (! yyres)
    return yystrlen (yystr);

  return yystpcpy (yyres, yystr) - yyres;
}
# endif

/* Copy into *YYMSG, which is of size *YYMSG_ALLOC, an error message
   about the unexpected token YYTOKEN for the state stack whose top is
   YYSSP.

   Return 0 if *YYMSG was successfully written.  Return 1 if *YYMSG is
   not large enough to hold the message.  In that case, also set
   *YYMSG_ALLOC to the required number of bytes.  Return 2 if the
   required number of bytes is too large to store.  */
static int
yysyntax_error (YYSIZE_T *yymsg_alloc, char **yymsg,
                yytype_int16 *yyssp, int yytoken)
{
  YYSIZE_T yysize0 = yytnamerr (YY_NULLPTR, yytname[yytoken]);
  YYSIZE_T yysize = yysize0;
  enum { YYERROR_VERBOSE_ARGS_MAXIMUM = 5 };
  /* Internationalized format string. */
  const char *yyformat = YY_NULLPTR;
  /* Arguments of yyformat. */
  char const *yyarg[YYERROR_VERBOSE_ARGS_MAXIMUM];
  /* Number of reported tokens (one for the "unexpected", one per
     "expected"). */
  int yycount = 0;

  /* There are many possibilities here to consider:
     - If this state is a consistent state with a default action, then
       the only way this function was invoked is if the default action
       is an error action.  In that case, don't check for expected
       tokens because there are none.
     - The only way there can be no lookahead present (in yychar) is if
       this state is a consistent state with a default action.  Thus,
       detecting the absence of a lookahead is sufficient to determine
       that there is no unexpected or expected token to report.  In that
       case, just report a simple "syntax error".
     - Don't assume there isn't a lookahead just because this state is a
       consistent state with a default action.  There might have been a
       previous inconsistent state, consistent state with a non-default
       action, or user semantic action that manipulated yychar.
     - Of course, the expected token list depends on states to have
       correct lookahead information, and it depends on the parser not
       to perform extra reductions after fetching a lookahead from the
       scanner and before detecting a syntax error.  Thus, state merging
       (from LALR or IELR) and default reductions corrupt the expected
       token list.  However, the list is correct for canonical LR with
       one exception: it will still contain any token that will not be
       accepted due to an error action in a later state.
  */
  if (yytoken != YYEMPTY)
    {
      int yyn = yypact[*yyssp];
      yyarg[yycount++] = yytname[yytoken];
      if (!yypact_value_is_default (yyn))
        {
          /* Start YYX at -YYN if negative to avoid negative indexes in
             YYCHECK.  In other words, skip the first -YYN actions for
             this state because they are default actions.  */
          int yyxbegin = yyn < 0 ? -yyn : 0;
          /* Stay within bounds of both yycheck and yytname.  */
          int yychecklim = YYLAST - yyn + 1;
          int yyxend = yychecklim < YYNTOKENS ? yychecklim : YYNTOKENS;
          int yyx;

          for (yyx = yyxbegin; yyx < yyxend; ++yyx)
            if (yycheck[yyx + yyn] == yyx && yyx != YYTERROR
                && !yytable_value_is_error (yytable[yyx + yyn]))
              {
                if (yycount == YYERROR_VERBOSE_ARGS_MAXIMUM)
                  {
                    yycount = 1;
                    yysize = yysize0;
                    break;
                  }
                yyarg[yycount++] = yytname[yyx];
                {
                  YYSIZE_T yysize1 = yysize + yytnamerr (YY_NULLPTR, yytname[yyx]);
                  if (! (yysize <= yysize1
                         && yysize1 <= YYSTACK_ALLOC_MAXIMUM))
                    return 2;
                  yysize = yysize1;
                }
              }
        }
    }

  switch (yycount)
    {
# define YYCASE_(N, S)                      \
      case N:                               \
        yyformat = S;                       \
      break
      YYCASE_(0, YY_("syntax error"));
      YYCASE_(1, YY_("syntax error, unexpected %s"));
      YYCASE_(2, YY_("syntax error, unexpected %s, expecting %s"));
      YYCASE_(3, YY_("syntax error, unexpected %s, expecting %s or %s"));
      YYCASE_(4, YY_("syntax error, unexpected %s, expecting %s or %s or %s"));
      YYCASE_(5, YY_("syntax error, unexpected %s, expecting %s or %s or %s or %s"));
# undef YYCASE_
    }

  {
    YYSIZE_T yysize1 = yysize + yystrlen (yyformat);
    if (! (yysize <= yysize1 && yysize1 <= YYSTACK_ALLOC_MAXIMUM))
      return 2;
    yysize = yysize1;
  }

  if (*yymsg_alloc < yysize)
    {
      *yymsg_alloc = 2 * yysize;
      if (! (yysize <= *yymsg_alloc
             && *yymsg_alloc <= YYSTACK_ALLOC_MAXIMUM))
        *yymsg_alloc = YYSTACK_ALLOC_MAXIMUM;
      return 1;
    }

  /* Avoid sprintf, as that infringes on the user's name space.
     Don't have undefined behavior even if the translation
     produced a string with the wrong number of "%s"s.  */
  {
    char *yyp = *yymsg;
    int yyi = 0;
    while ((*yyp = *yyformat) != '\0')
      if (*yyp == '%' && yyformat[1] == 's' && yyi < yycount)
        {
          yyp += yytnamerr (yyp, yyarg[yyi++]);
          yyformat += 2;
        }
      else
        {
          yyp++;
          yyformat++;
        }
  }
  return 0;
}
#endif /* YYERROR_VERBOSE */

/*-----------------------------------------------.
| Release the memory associated to this symbol.  |
`-----------------------------------------------*/

static void
yydestruct (const char *yymsg, int yytype, YYSTYPE *yyvaluep)
{
  YYUSE (yyvaluep);
  if (!yymsg)
    yymsg = "Deleting";
  YY_SYMBOL_PRINT (yymsg, yytype, yyvaluep, yylocationp);

  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  YYUSE (yytype);
  YY_IGNORE_MAYBE_UNINITIALIZED_END
}




/* The lookahead symbol.  */
int yychar;

/* The semantic value of the lookahead symbol.  */
YYSTYPE yylval;
/* Number of syntax errors so far.  */
int yynerrs;


/*----------.
| yyparse.  |
`----------*/

int
yyparse (void)
{
    int yystate;
    /* Number of tokens to shift before error messages enabled.  */
    int yyerrstatus;

    /* The stacks and their tools:
       'yyss': related to states.
       'yyvs': related to semantic values.

       Refer to the stacks through separate pointers, to allow yyoverflow
       to reallocate them elsewhere.  */

    /* The state stack.  */
    yytype_int16 yyssa[YYINITDEPTH];
    yytype_int16 *yyss;
    yytype_int16 *yyssp;

    /* The semantic value stack.  */
    YYSTYPE yyvsa[YYINITDEPTH];
    YYSTYPE *yyvs;
    YYSTYPE *yyvsp;

    YYSIZE_T yystacksize;

  int yyn;
  int yyresult;
  /* Lookahead token as an internal (translated) token number.  */
  int yytoken = 0;
  /* The variables used to return semantic value and location from the
     action routines.  */
  YYSTYPE yyval;

#if YYERROR_VERBOSE
  /* Buffer for error messages, and its allocated size.  */
  char yymsgbuf[128];
  char *yymsg = yymsgbuf;
  YYSIZE_T yymsg_alloc = sizeof yymsgbuf;
#endif

#define YYPOPSTACK(N)   (yyvsp -= (N), yyssp -= (N))

  /* The number of symbols on the RHS of the reduced rule.
     Keep to zero when no symbol should be popped.  */
  int yylen = 0;

  yyssp = yyss = yyssa;
  yyvsp = yyvs = yyvsa;
  yystacksize = YYINITDEPTH;

  YYDPRINTF ((stderr, "Starting parse\n"));

  yystate = 0;
  yyerrstatus = 0;
  yynerrs = 0;
  yychar = YYEMPTY; /* Cause a token to be read.  */
  goto yysetstate;

/*------------------------------------------------------------.
| yynewstate -- Push a new state, which is found in yystate.  |
`------------------------------------------------------------*/
 yynewstate:
  /* In all cases, when you get here, the value and location stacks
     have just been pushed.  So pushing a state here evens the stacks.  */
  yyssp++;

 yysetstate:
  *yyssp = yystate;

  if (yyss + yystacksize - 1 <= yyssp)
    {
      /* Get the current used size of the three stacks, in elements.  */
      YYSIZE_T yysize = yyssp - yyss + 1;

#ifdef yyoverflow
      {
        /* Give user a chance to reallocate the stack.  Use copies of
           these so that the &'s don't force the real ones into
           memory.  */
        YYSTYPE *yyvs1 = yyvs;
        yytype_int16 *yyss1 = yyss;

        /* Each stack pointer address is followed by the size of the
           data in use in that stack, in bytes.  This used to be a
           conditional around just the two extra args, but that might
           be undefined if yyoverflow is a macro.  */
        yyoverflow (YY_("memory exhausted"),
                    &yyss1, yysize * sizeof (*yyssp),
                    &yyvs1, yysize * sizeof (*yyvsp),
                    &yystacksize);

        yyss = yyss1;
        yyvs = yyvs1;
      }
#else /* no yyoverflow */
# ifndef YYSTACK_RELOCATE
      goto yyexhaustedlab;
# else
      /* Extend the stack our own way.  */
      if (YYMAXDEPTH <= yystacksize)
        goto yyexhaustedlab;
      yystacksize *= 2;
      if (YYMAXDEPTH < yystacksize)
        yystacksize = YYMAXDEPTH;

      {
        yytype_int16 *yyss1 = yyss;
        union yyalloc *yyptr =
          (union yyalloc *) YYSTACK_ALLOC (YYSTACK_BYTES (yystacksize));
        if (! yyptr)
          goto yyexhaustedlab;
        YYSTACK_RELOCATE (yyss_alloc, yyss);
        YYSTACK_RELOCATE (yyvs_alloc, yyvs);
#  undef YYSTACK_RELOCATE
        if (yyss1 != yyssa)
          YYSTACK_FREE (yyss1);
      }
# endif
#endif /* no yyoverflow */

      yyssp = yyss + yysize - 1;
      yyvsp = yyvs + yysize - 1;

      YYDPRINTF ((stderr, "Stack size increased to %lu\n",
                  (unsigned long int) yystacksize));

      if (yyss + yystacksize - 1 <= yyssp)
        YYABORT;
    }

  YYDPRINTF ((stderr, "Entering state %d\n", yystate));

  if (yystate == YYFINAL)
    YYACCEPT;

  goto yybackup;

/*-----------.
| yybackup.  |
`-----------*/
yybackup:

  /* Do appropriate processing given the current state.  Read a
     lookahead token if we need one and don't already have one.  */

  /* First try to decide what to do without reference to lookahead token.  */
  yyn = yypact[yystate];
  if (yypact_value_is_default (yyn))
    goto yydefault;

  /* Not known => get a lookahead token if don't already have one.  */

  /* YYCHAR is either YYEMPTY or YYEOF or a valid lookahead symbol.  */
  if (yychar == YYEMPTY)
    {
      YYDPRINTF ((stderr, "Reading a token: "));
      yychar = yylex ();
    }

  if (yychar <= YYEOF)
    {
      yychar = yytoken = YYEOF;
      YYDPRINTF ((stderr, "Now at end of input.\n"));
    }
  else
    {
      yytoken = YYTRANSLATE (yychar);
      YY_SYMBOL_PRINT ("Next token is", yytoken, &yylval, &yylloc);
    }

  /* If the proper action on seeing token YYTOKEN is to reduce or to
     detect an error, take that action.  */
  yyn += yytoken;
  if (yyn < 0 || YYLAST < yyn || yycheck[yyn] != yytoken)
    goto yydefault;
  yyn = yytable[yyn];
  if (yyn <= 0)
    {
      if (yytable_value_is_error (yyn))
        goto yyerrlab;
      yyn = -yyn;
      goto yyreduce;
    }

  /* Count tokens shifted since error; after three, turn off error
     status.  */
  if (yyerrstatus)
    yyerrstatus--;

  /* Shift the lookahead token.  */
  YY_SYMBOL_PRINT ("Shifting", yytoken, &yylval, &yylloc);

  /* Discard the shifted token.  */
  yychar = YYEMPTY;

  yystate = yyn;
  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  *++yyvsp = yylval;
  YY_IGNORE_MAYBE_UNINITIALIZED_END

  goto yynewstate;


/*-----------------------------------------------------------.
| yydefault -- do the default action for the current state.  |
`-----------------------------------------------------------*/
yydefault:
  yyn = yydefact[yystate];
  if (yyn == 0)
    goto yyerrlab;
  goto yyreduce;


/*-----------------------------.
| yyreduce -- Do a reduction.  |
`-----------------------------*/
yyreduce:
  /* yyn is the number of a rule to reduce with.  */
  yylen = yyr2[yyn];

  /* If YYLEN is nonzero, implement the default value of the action:
     '$$ = $1'.

     Otherwise, the following line sets YYVAL to garbage.
     This behavior is undocumented and Bison
     users should not rely upon it.  Assigning to YYVAL
     unconditionally makes the parser a bit smaller, and it avoids a
     GCC warning that YYVAL may be used uninitialized.  */
  yyval = yyvsp[1-yylen];


  YY_REDUCE_PRINT (yyn);
  switch (yyn)
    {
        case 2:

    {(yyval.CRCLStatusFileVal) = new CRCLStatusFile((yyvsp[-4].XmlVersionVal), (yyvsp[-2].XmlHeaderForCRCLStatusVal), (yyvsp[-1].CRCLStatusTypeVal));
	   CRCLStatusTree = (yyval.CRCLStatusFileVal);
	   if (XmlIDREF::idMissing())
	     yyerror("xs:ID missing for xs:IDREF");
	  }

    break;

  case 3:

    {(yyval.XmlHeaderForCRCLStatusVal) = new XmlHeaderForCRCLStatus((yyvsp[0].SchemaLocationVal));}

    break;

  case 4:

    {(yyval.SchemaLocationVal) = new SchemaLocation("xsi", (yyvsp[0].sVal), false);
	   free((yyvsp[0].sVal));
	  }

    break;

  case 5:

    {(yyval.XmlBooleanVal) = new XmlBoolean((yyvsp[0].sVal));
	   if ((yyval.XmlBooleanVal)->bad)
	     yyerror("bad XmlBoolean");
	   free((yyvsp[0].sVal));
	  }

    break;

  case 6:

    {(yyval.XmlDecimalVal) = new XmlDecimal((yyvsp[0].sVal));
	   if ((yyval.XmlDecimalVal)->bad)
	     yyerror("bad XmlDecimal");
	   free((yyvsp[0].sVal));
	  }

    break;

  case 7:

    {(yyval.XmlIDVal) = new XmlID((yyvsp[0].sVal));
	   if ((yyval.XmlIDVal)->bad)
	     yyerror("bad XmlID");
	   free((yyvsp[0].sVal));
	  }

    break;

  case 8:

    {(yyval.XmlNMTOKENVal) = new XmlNMTOKEN((yyvsp[0].sVal));
	   if ((yyval.XmlNMTOKENVal)->bad)
	     yyerror("bad XmlNMTOKEN");
	   free((yyvsp[0].sVal));
	  }

    break;

  case 9:

    {(yyval.XmlNonNegativeIntegerVal) = new XmlNonNegativeInteger((yyvsp[0].sVal));
	   if ((yyval.XmlNonNegativeIntegerVal)->bad)
	     yyerror("bad XmlNonNegativeInteger");
	   free((yyvsp[0].sVal));
	  }

    break;

  case 10:

    {(yyval.XmlPositiveIntegerVal) = new XmlPositiveInteger((yyvsp[0].sVal));
	   if ((yyval.XmlPositiveIntegerVal)->bad)
	     yyerror("bad XmlPositiveInteger");
	   free((yyvsp[0].sVal));
	  }

    break;

  case 11:

    {(yyval.XmlStringVal) = new XmlString((yyvsp[0].sVal));
	   if ((yyval.XmlStringVal)->bad)
	     yyerror("bad XmlString");
	   free((yyvsp[0].sVal));
	  }

    break;

  case 12:

    {(yyval.XmlVersionVal) = new XmlVersion(false);
	   if (strcmp((yyvsp[-1].sVal), "1.0"))
	     yyerror("version number must be 1.0");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 13:

    {(yyval.XmlVersionVal) = new XmlVersion(true);
	   if (strcmp((yyvsp[-3].sVal), "1.0"))
	     yyerror("version number must be 1.0");
	   else if ((strcmp((yyvsp[-1].sVal), "UTF-8")) && (strcmp((yyvsp[-1].sVal), "utf-8")))
	     yyerror("encoding must be UTF-8");
	   free((yyvsp[-3].sVal));
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 14:

    {(yyval.VectorTypeVal) = (yyvsp[-1].VectorTypeVal);}

    break;

  case 15:

    {(yyval.CRCLStatusTypeVal) = new CRCLStatusType((yyvsp[-4].XmlIDVal), (yyvsp[-3].CommandStatusTypeVal), (yyvsp[-2].JointStatusesTypeVal), (yyvsp[-1].PoseStatusTypeVal), (yyvsp[0].GripperStatusTypeVal));}

    break;

  case 16:

    {yyReadData = 1;}

    break;

  case 17:

    {(yyval.XmlNonNegativeIntegerVal) = (yyvsp[-1].XmlNonNegativeIntegerVal);}

    break;

  case 18:

    {yyReadData = 1;}

    break;

  case 19:

    {(yyval.CommandStateEnumTypeVal) = new CommandStateEnumType((yyvsp[-1].sVal));
	   if ((yyval.CommandStateEnumTypeVal)->bad)
	     yyerror("bad CommandState value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 20:

    {(yyval.CommandStatusTypeVal) = new CommandStatusType((yyvsp[-4].XmlIDVal), (yyvsp[-3].XmlNonNegativeIntegerVal), (yyvsp[-2].XmlPositiveIntegerVal), (yyvsp[-1].CommandStateEnumTypeVal), (yyvsp[0].XmlStringVal));}

    break;

  case 21:

    {(yyval.CommandStatusTypeVal) = (yyvsp[-1].CommandStatusTypeVal);}

    break;

  case 22:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 23:

    {yyReadData = 1;}

    break;

  case 24:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 25:

    {(yyval.FractionTypeVal) = 0;}

    break;

  case 26:

    {yyReadData = 1;}

    break;

  case 27:

    {(yyval.FractionTypeVal) = new FractionType((yyvsp[-1].sVal));
	   if ((yyval.FractionTypeVal)->bad)
	     yyerror("bad Finger1Position value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 28:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 29:

    {yyReadData = 1;}

    break;

  case 30:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 31:

    {(yyval.FractionTypeVal) = 0;}

    break;

  case 32:

    {yyReadData = 1;}

    break;

  case 33:

    {(yyval.FractionTypeVal) = new FractionType((yyvsp[-1].sVal));
	   if ((yyval.FractionTypeVal)->bad)
	     yyerror("bad Finger2Position value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 34:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 35:

    {yyReadData = 1;}

    break;

  case 36:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 37:

    {(yyval.FractionTypeVal) = 0;}

    break;

  case 38:

    {yyReadData = 1;}

    break;

  case 39:

    {(yyval.FractionTypeVal) = new FractionType((yyvsp[-1].sVal));
	   if ((yyval.FractionTypeVal)->bad)
	     yyerror("bad Finger3Position value");
	   free((yyvsp[-1].sVal));
	  }

    break;

  case 40:

    {(yyval.VectorTypeVal) = (yyvsp[-1].VectorTypeVal);}

    break;

  case 41:

    {yyReadData = 1;}

    break;

  case 42:

    {(yyval.XmlNMTOKENVal) = (yyvsp[-1].XmlNMTOKENVal);}

    break;

  case 43:

    {(yyval.GripperStatusTypeVal) = (yyvsp[0].ParallelGripperStatusTypeVal);}

    break;

  case 44:

    {(yyval.GripperStatusTypeVal) = (yyvsp[0].ThreeFingerGripperStatusTypeVal);}

    break;

  case 45:

    {(yyval.GripperStatusTypeVal) = (yyvsp[0].VacuumGripperStatusTypeVal);}

    break;

  case 46:

    {(yyval.GripperStatusTypeVal) = 0;}

    break;

  case 47:

    {(yyval.GripperStatusTypeVal) = (yyvsp[-1].GripperStatusTypeVal);}

    break;

  case 48:

    {yyReadData = 1;}

    break;

  case 49:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 50:

    {yyReadData = 1;}

    break;

  case 51:

    {(yyval.XmlBooleanVal) = (yyvsp[-1].XmlBooleanVal);}

    break;

  case 52:

    {yyReadData = 1;}

    break;

  case 53:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 54:

    {yyReadData = 1;}

    break;

  case 55:

    {(yyval.XmlPositiveIntegerVal) = (yyvsp[-1].XmlPositiveIntegerVal);}

    break;

  case 56:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 57:

    {yyReadData = 1;}

    break;

  case 58:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 59:

    {(yyval.JointStatusTypeVal) = new JointStatusType((yyvsp[-4].XmlIDVal), (yyvsp[-3].XmlPositiveIntegerVal), (yyvsp[-2].XmlDecimalVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));}

    break;

  case 60:

    {(yyval.JointStatusTypeVal) = (yyvsp[-1].JointStatusTypeVal);}

    break;

  case 61:

    {(yyval.JointStatusesTypeVal) = new JointStatusesType((yyvsp[-1].XmlIDVal), (yyvsp[0].ListJointStatusTypeVal));}

    break;

  case 62:

    {(yyval.JointStatusesTypeVal) = 0;}

    break;

  case 63:

    {(yyval.JointStatusesTypeVal) = (yyvsp[-1].JointStatusesTypeVal);}

    break;

  case 64:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 65:

    {yyReadData = 1;}

    break;

  case 66:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 67:

    {(yyval.XmlDecimalVal) = 0;}

    break;

  case 68:

    {yyReadData = 1;}

    break;

  case 69:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 70:

    {yyReadData = 1;}

    break;

  case 71:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 72:

    {(yyval.VectorTypeVal) = (yyvsp[-1].VectorTypeVal);}

    break;

  case 73:

    {(yyval.ListJointStatusTypeVal) = new std::list<JointStatusType *>;
	   (yyval.ListJointStatusTypeVal)->push_back((yyvsp[0].JointStatusTypeVal));}

    break;

  case 74:

    {(yyval.ListJointStatusTypeVal) = (yyvsp[-1].ListJointStatusTypeVal);
	   (yyval.ListJointStatusTypeVal)->push_back((yyvsp[0].JointStatusTypeVal));}

    break;

  case 75:

    {(yyval.VectorTypeVal) = (yyvsp[-1].VectorTypeVal);}

    break;

  case 76:

    {(yyval.XmlIDVal) = 0;}

    break;

  case 77:

    {yyReadData = 1;}

    break;

  case 78:

    {(yyval.XmlIDVal) = (yyvsp[-1].XmlIDVal);}

    break;

  case 79:

    {(yyval.PointTypeVal) = new PointType((yyvsp[-3].XmlIDVal), (yyvsp[-2].XmlDecimalVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));}

    break;

  case 80:

    {(yyval.PointTypeVal) = (yyvsp[-1].PointTypeVal);}

    break;

  case 81:

    {(yyval.PoseStatusTypeVal) = new PoseStatusType((yyvsp[-3].XmlIDVal), (yyvsp[-2].PoseTypeVal), (yyvsp[-1].TwistTypeVal), (yyvsp[0].WrenchTypeVal));}

    break;

  case 82:

    {(yyval.PoseStatusTypeVal) = 0;}

    break;

  case 83:

    {(yyval.PoseStatusTypeVal) = (yyvsp[-1].PoseStatusTypeVal);}

    break;

  case 84:

    {(yyval.PoseTypeVal) = new PoseType((yyvsp[-3].XmlIDVal), (yyvsp[-2].PointTypeVal), (yyvsp[-1].VectorTypeVal), (yyvsp[0].VectorTypeVal));}

    break;

  case 85:

    {(yyval.PoseTypeVal) = (yyvsp[-1].PoseTypeVal);}

    break;

  case 86:

    {yyReadData = 1;}

    break;

  case 87:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 88:

    {(yyval.XmlStringVal) = 0;}

    break;

  case 89:

    {yyReadData = 1;}

    break;

  case 90:

    {(yyval.XmlStringVal) = (yyvsp[-1].XmlStringVal);}

    break;

  case 91:

    {yyReadData = 1;}

    break;

  case 92:

    {(yyval.XmlPositiveIntegerVal) = (yyvsp[-1].XmlPositiveIntegerVal);}

    break;

  case 93:

    {(yyval.TwistTypeVal) = new TwistType((yyvsp[-2].XmlIDVal), (yyvsp[-1].VectorTypeVal), (yyvsp[0].VectorTypeVal));}

    break;

  case 94:

    {(yyval.TwistTypeVal) = 0;}

    break;

  case 95:

    {(yyval.TwistTypeVal) = (yyvsp[-1].TwistTypeVal);}

    break;

  case 96:

    {(yyval.VectorTypeVal) = new VectorType((yyvsp[-3].XmlIDVal), (yyvsp[-2].XmlDecimalVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));}

    break;

  case 97:

    {(yyval.WrenchTypeVal) = new WrenchType((yyvsp[-2].XmlIDVal), (yyvsp[-1].VectorTypeVal), (yyvsp[0].VectorTypeVal));}

    break;

  case 98:

    {(yyval.WrenchTypeVal) = 0;}

    break;

  case 99:

    {(yyval.WrenchTypeVal) = (yyvsp[-1].WrenchTypeVal);}

    break;

  case 100:

    {(yyval.VectorTypeVal) = (yyvsp[-1].VectorTypeVal);}

    break;

  case 101:

    {yyReadData = 1;}

    break;

  case 102:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 103:

    {yyReadData = 1;}

    break;

  case 104:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 105:

    {(yyval.VectorTypeVal) = (yyvsp[-1].VectorTypeVal);}

    break;

  case 106:

    {yyReadData = 1;}

    break;

  case 107:

    {(yyval.XmlDecimalVal) = (yyvsp[-1].XmlDecimalVal);}

    break;

  case 108:

    {(yyval.ParallelGripperStatusTypeVal) = new ParallelGripperStatusType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlNMTOKENVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.ParallelGripperStatusTypeVal)->printTypp = true;
	  }

    break;

  case 109:

    {(yyval.ThreeFingerGripperStatusTypeVal) = new ThreeFingerGripperStatusType((yyvsp[-7].XmlIDVal), (yyvsp[-6].XmlNMTOKENVal), (yyvsp[-5].FractionTypeVal), (yyvsp[-4].FractionTypeVal), (yyvsp[-3].FractionTypeVal), (yyvsp[-2].XmlDecimalVal), (yyvsp[-1].XmlDecimalVal), (yyvsp[0].XmlDecimalVal));
	   (yyval.ThreeFingerGripperStatusTypeVal)->printTypp = true;
	  }

    break;

  case 110:

    {(yyval.VacuumGripperStatusTypeVal) = new VacuumGripperStatusType((yyvsp[-2].XmlIDVal), (yyvsp[-1].XmlNMTOKENVal), (yyvsp[0].XmlBooleanVal));
	   (yyval.VacuumGripperStatusTypeVal)->printTypp = true;
	  }

    break;



      default: break;
    }
  /* User semantic actions sometimes alter yychar, and that requires
     that yytoken be updated with the new translation.  We take the
     approach of translating immediately before every use of yytoken.
     One alternative is translating here after every semantic action,
     but that translation would be missed if the semantic action invokes
     YYABORT, YYACCEPT, or YYERROR immediately after altering yychar or
     if it invokes YYBACKUP.  In the case of YYABORT or YYACCEPT, an
     incorrect destructor might then be invoked immediately.  In the
     case of YYERROR or YYBACKUP, subsequent parser actions might lead
     to an incorrect destructor call or verbose syntax error message
     before the lookahead is translated.  */
  YY_SYMBOL_PRINT ("-> $$ =", yyr1[yyn], &yyval, &yyloc);

  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);

  *++yyvsp = yyval;

  /* Now 'shift' the result of the reduction.  Determine what state
     that goes to, based on the state we popped back to and the rule
     number reduced by.  */

  yyn = yyr1[yyn];

  yystate = yypgoto[yyn - YYNTOKENS] + *yyssp;
  if (0 <= yystate && yystate <= YYLAST && yycheck[yystate] == *yyssp)
    yystate = yytable[yystate];
  else
    yystate = yydefgoto[yyn - YYNTOKENS];

  goto yynewstate;


/*--------------------------------------.
| yyerrlab -- here on detecting error.  |
`--------------------------------------*/
yyerrlab:
  /* Make sure we have latest lookahead translation.  See comments at
     user semantic actions for why this is necessary.  */
  yytoken = yychar == YYEMPTY ? YYEMPTY : YYTRANSLATE (yychar);

  /* If not already recovering from an error, report this error.  */
  if (!yyerrstatus)
    {
      ++yynerrs;
#if ! YYERROR_VERBOSE
      yyerror (YY_("syntax error"));
#else
# define YYSYNTAX_ERROR yysyntax_error (&yymsg_alloc, &yymsg, \
                                        yyssp, yytoken)
      {
        char const *yymsgp = YY_("syntax error");
        int yysyntax_error_status;
        yysyntax_error_status = YYSYNTAX_ERROR;
        if (yysyntax_error_status == 0)
          yymsgp = yymsg;
        else if (yysyntax_error_status == 1)
          {
            if (yymsg != yymsgbuf)
              YYSTACK_FREE (yymsg);
            yymsg = (char *) YYSTACK_ALLOC (yymsg_alloc);
            if (!yymsg)
              {
                yymsg = yymsgbuf;
                yymsg_alloc = sizeof yymsgbuf;
                yysyntax_error_status = 2;
              }
            else
              {
                yysyntax_error_status = YYSYNTAX_ERROR;
                yymsgp = yymsg;
              }
          }
        yyerror (yymsgp);
        if (yysyntax_error_status == 2)
          goto yyexhaustedlab;
      }
# undef YYSYNTAX_ERROR
#endif
    }



  if (yyerrstatus == 3)
    {
      /* If just tried and failed to reuse lookahead token after an
         error, discard it.  */

      if (yychar <= YYEOF)
        {
          /* Return failure if at end of input.  */
          if (yychar == YYEOF)
            YYABORT;
        }
      else
        {
          yydestruct ("Error: discarding",
                      yytoken, &yylval);
          yychar = YYEMPTY;
        }
    }

  /* Else will try to reuse lookahead token after shifting the error
     token.  */
  goto yyerrlab1;


/*---------------------------------------------------.
| yyerrorlab -- error raised explicitly by YYERROR.  |
`---------------------------------------------------*/
yyerrorlab:

  /* Pacify compilers like GCC when the user code never invokes
     YYERROR and the label yyerrorlab therefore never appears in user
     code.  */
  if (/*CONSTCOND*/ 0)
     goto yyerrorlab;

  /* Do not reclaim the symbols of the rule whose action triggered
     this YYERROR.  */
  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);
  yystate = *yyssp;
  goto yyerrlab1;


/*-------------------------------------------------------------.
| yyerrlab1 -- common code for both syntax error and YYERROR.  |
`-------------------------------------------------------------*/
yyerrlab1:
  yyerrstatus = 3;      /* Each real token shifted decrements this.  */

  for (;;)
    {
      yyn = yypact[yystate];
      if (!yypact_value_is_default (yyn))
        {
          yyn += YYTERROR;
          if (0 <= yyn && yyn <= YYLAST && yycheck[yyn] == YYTERROR)
            {
              yyn = yytable[yyn];
              if (0 < yyn)
                break;
            }
        }

      /* Pop the current state because it cannot handle the error token.  */
      if (yyssp == yyss)
        YYABORT;


      yydestruct ("Error: popping",
                  yystos[yystate], yyvsp);
      YYPOPSTACK (1);
      yystate = *yyssp;
      YY_STACK_PRINT (yyss, yyssp);
    }

  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  *++yyvsp = yylval;
  YY_IGNORE_MAYBE_UNINITIALIZED_END


  /* Shift the error token.  */
  YY_SYMBOL_PRINT ("Shifting", yystos[yyn], yyvsp, yylsp);

  yystate = yyn;
  goto yynewstate;


/*-------------------------------------.
| yyacceptlab -- YYACCEPT comes here.  |
`-------------------------------------*/
yyacceptlab:
  yyresult = 0;
  goto yyreturn;

/*-----------------------------------.
| yyabortlab -- YYABORT comes here.  |
`-----------------------------------*/
yyabortlab:
  yyresult = 1;
  goto yyreturn;

#if !defined yyoverflow || YYERROR_VERBOSE
/*-------------------------------------------------.
| yyexhaustedlab -- memory exhaustion comes here.  |
`-------------------------------------------------*/
yyexhaustedlab:
  yyerror (YY_("memory exhausted"));
  yyresult = 2;
  /* Fall through.  */
#endif

yyreturn:
  if (yychar != YYEMPTY)
    {
      /* Make sure we have latest lookahead translation.  See comments at
         user semantic actions for why this is necessary.  */
      yytoken = YYTRANSLATE (yychar);
      yydestruct ("Cleanup: discarding lookahead",
                  yytoken, &yylval);
    }
  /* Do not reclaim the symbols of the rule whose action triggered
     this YYABORT or YYACCEPT.  */
  YYPOPSTACK (yylen);
  YY_STACK_PRINT (yyss, yyssp);
  while (yyssp != yyss)
    {
      yydestruct ("Cleanup: popping",
                  yystos[*yyssp], yyvsp);
      YYPOPSTACK (1);
    }
#ifndef yyoverflow
  if (yyss != yyssa)
    YYSTACK_FREE (yyss);
#endif
#if YYERROR_VERBOSE
  if (yymsg != yymsgbuf)
    YYSTACK_FREE (yymsg);
#endif
  return yyresult;
}



/*********************************************************************/

/* yyerror

Returned Value: int (0)

Called By: yyparse

This prints whatever string the parser provides.

*/

int yyerror(      /* ARGUMENTS       */
 const char * s)  /* string to print */
{
  fflush(stdout);
  fprintf(stderr, "\n%s\n", s);
  exit(1);
  return 0;
}

/*********************************************************************/
