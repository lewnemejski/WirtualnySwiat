#include <time.h>
#include <cstdlib>
#include "Swiat.h"

using namespace std;

int main()
{
	system("title Kacper Wszeborowski 189477");
	srand(time(NULL));
	Swiat swiat(SWIAT_X, SWIAT_Y);
	swiat.start();
	return 0;
}