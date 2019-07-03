#include <stdlib.h>
#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include "URDF_CRPI.hxx"

using namespace std;

/*
  Define if you don't want to check each message against the schema
  indicated in the message.
*/
#define DONT_VALIDATE 1

int main (int argc, char* argv[])
{
  filebuf fb;
  if (! fb.open(argv[1], ios::in)) {
    cerr << "usage: URDF_CRPI_Test <input status file>" << endl;
    return 1;
  }
  
  istream is(&fb);

  try {
    auto_ptr<robot> myrobot(robot_(is
				   #ifdef DONT_VALIDATE
				   ,xml_schema::flags::dont_validate
				   #endif
			      ));
    cout << myrobot->name() << endl;

    cout << myrobot->TCP_IP().Address()
	 << " " << myrobot->TCP_IP().Port()
	 << " " << (myrobot->TCP_IP().Client() ? "client" : "not a client")
	 << endl;
    
    if (NULL != myrobot->ComType()) {
      cout << "ComType: " << myrobot->ComType()->Val() << endl;
    } else {
      cout << "(no ComType)" << endl;
    }

    if (NULL != myrobot->Mounting()) {
      cout << "Mounting:"
	   << " " << myrobot->Mounting()->X()
	   << " " << myrobot->Mounting()->Y()
	   << " " << myrobot->Mounting()->Z()
	   << " " << myrobot->Mounting()->XR()
	   << " " << myrobot->Mounting()->YR()
	   << " " << myrobot->Mounting()->ZR()
	   << endl;
    } else {
      cout << "(no Mounting)" << endl;
    }

    robot::Tool_iterator tool_iter;
    for (tool_iter = myrobot->Tool().begin(); tool_iter != myrobot->Tool().end(); tool_iter++) {
      cout << "Tool " << tool_iter->ID() << ": " << tool_iter->Name() << endl;
    }

    robot::link_iterator link_iter;
    for (link_iter = myrobot->link().begin(); link_iter != myrobot->link().end(); link_iter++) {
      cout << "Link: " << link_iter->name() << endl;
    }

    robot::joint_iterator joint_iter;
    for (joint_iter = myrobot->joint().begin(); joint_iter != myrobot->joint().end(); joint_iter++) {
      cout << "Joint:"
	   << " " << joint_iter->name()
	   << " " << joint_iter->type()
	   << endl;
    }
      
  } catch (const xml_schema::exception& e) {
    cerr << e << endl;
    return 1;
  }

  return 0;
}


