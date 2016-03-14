#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include <tf/transform_datatypes.h>

int main(int argc, char *argv[])
{
  double r, p, y;
  tf::Matrix3x3 m;
  tf::Vector3 v1, v2, v3;
  tf::Quaternion q;

  while (! feof(stdin)) {
#define BUFFERLEN 256
    char buffer[BUFFERLEN];

    fgets(buffer, sizeof(buffer), stdin);
    buffer[sizeof(buffer)-1] = 0;

    if (3 != sscanf(buffer, "%lf %lf %lf", &r, &p, &y)) {
      printf("need r p y\n");
      continue;
    }
    m.setRPY(r, p, y);

    v1 = m.getColumn(0);
    v2 = m.getColumn(1);
    v3 = m.getColumn(2);

    // the rotation matrix, by visual columns
    printf("%f %f %f\n", v1.getX(), v2.getX(), v3.getX());
    printf("%f %f %f\n", v1.getY(), v2.getY(), v3.getY());
    printf("%f %f %f\n", v1.getZ(), v2.getZ(), v3.getZ());

    // the rotation matrix, by matrix[row][column]
    printf("---\n");
    printf("%f %f %f\n", m[0][0], m[0][1], m[0][2]);
    printf("%f %f %f\n", m[1][0], m[1][1], m[1][2]);
    printf("%f %f %f\n", m[2][0], m[2][1], m[2][2]);
    tf::Matrix3x3 mout(v1.getX(), v2.getX(), v3.getX(),
		       v1.getY(), v2.getY(), v3.getY(),
		       v1.getZ(), v2.getZ(), v3.getZ());

    // the quaternion
    q.setRPY(r, p, y);
    printf("---\n");
    printf("%f %f %f %f\n", q.getX(), q.getY(), q.getZ(), q.getW());

    // check the output rpy
    mout.getRPY(r, p, y);
    printf("---\n");
    printf("%f %f %f\n", r, p, y);
  }
}
