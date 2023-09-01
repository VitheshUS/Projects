#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "glut.h"

void displayfront()
{
	glClearColor(1, 1, 1, 1);
	glClear(GL_COLOR_BUFFER_BIT);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluOrtho2D(0, 1000, 0, 1000);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	glBegin(GL_POLYGON);
	glColor3f(0.439, 0.502, 0.565);
	glVertex2f(0, 500);
	glVertex2f(500, 500);
	glVertex2f(1000, 500);
	glEnd();
	char str1[] = "SAHYADRI COLLEGE OF ENGINEERING AND MANAGEMENT";
	glColor3f(0, 0, 0);
	glRasterPos2f(250, 950);

	int i = 0;
	while (str1[i] != '\0')
	{
		glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_24, str1[i]);
		i++;
	}

	char str2[] = "ADYAR,MANGALURU:574143";
	glColor3f(0, 0, 0);
	glRasterPos2f(350, 900);
	i = 0;
	while (str2[i] != '\0')
	{
		glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_24, str2[i]);
		i++;
	}

	char str3[] = "DEPARTMENT OF COMPUTER SCIENCE AND ENGINEERING";
	glColor3f(0, 0, 1);
	glRasterPos2f(220, 820);
	i = 0;
	while (str3[i] != '\0')
	{
		glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_24, str3[i]);
		i++;
	}

	char str4[] = "COMPUTER GRAPHICS AND VISUALIZATION";
	glColor3f(0, 0, 1);
	glRasterPos2f(280, 770);
	i = 0;
	while (str4[i] != '\0')
	{
		glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_24, str4[i]);
		i++;
	}

	char str5[] = "MINI PROJECT";
	glColor3f(0, 0, 0);
	glRasterPos2f(390, 690);
	i = 0;
	while (str5[i] != '\0')
	{
		glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_24, str5[i]);
		i++;
	}

	char str6[] = "ON";
	glColor3f(0, 0, 0);
	glRasterPos2f(440, 640);
	i = 0;
	while (str6[i] != '\0')
	{
		glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_24, str6[i]);
		i++;
	}

	char str16[] = "The Hungry Trex Movie trailer";
	glColor3f(0, 0, 1);
	glRasterPos2f(330, 570);
	i = 0;
	while (str16[i] != '\0')
	{
		glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_24, str16[i]);
		i++;
	}

	char str7[] = "TEAM MEMBERS:";
	glColor3f(0, 0, 0);
	glRasterPos2f(100, 400);
	i = 0;
	while (str7[i] != '\0')
	{
		glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_24, str7[i]);
		i++;
	}

	char str8[]= "Vithesh U S -4SF20CS180";
	glColor3f(0, 0.3, 1);
	glRasterPos2f(100, 350);
	i = 0;
	while (str8[i] != '\0')
	{
		glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_24, str8[i]);
		i++;
	}

	char str9[] = "Pavan Kumar B -4SF20CS409";
	glColor3f(0, 0.3, 1);
	glRasterPos2f(100, 300);
	i = 0;
	while (str9[i] != '\0')
	{
		glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_24, str9[i]);
		i++;
	}

	char str10[] = "DEPT OF CSE,SEC B";
	glColor3f(0, 0, 0);
	glRasterPos2f(100, 250);
	i = 0;
	while (str10[i] != '\0')
	{
		glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_24, str10[i]);
		i++;
	}

	char str11[] = "PROJECT GUIDED BY:";
	glColor3f(0, 0, 0);
	glRasterPos2f(700, 400);
	i = 0;
	while (str11[i] != '\0')
	{
		glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_24, str11[i]);
		i++;
	}

	char str12[] = "Ms. Megha Rani";
	glColor3f(0, 0.3, 1);
	glRasterPos2f(700, 350);
	i = 0;
	while (str12[i] != '\0')
	{
		glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_24, str12[i]);
		i++;
	}

	char str13[] = "Associate Professor";
	glColor3f(0, 0.3, 1);
	glRasterPos2f(700, 300);
	i = 0;
	while (str13[i] != '\0')
	{
		glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_24, str13[i]);
		i++;
	}

	char str14[] = "DEPT OF CSE";
	glColor3f(0, 0, 0);
	glRasterPos2f(700, 250);
	i = 0;
	while (str14[i] != '\0')
	{
		glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_24, str14[i]);
		i++;
	}

	glFlush();
}

