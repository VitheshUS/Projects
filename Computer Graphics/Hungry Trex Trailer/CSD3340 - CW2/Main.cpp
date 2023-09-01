#include <stdlib.h>
#include<math.h>
#include <stdio.h>
#include<string.h>
#include "glut.h"
#include "globaldeclare.h"
#include "dino.h"
#include "man.h"
#include "sdino.h"
#include "manRight.h"
#include "dinoRight.h"
#include "frontPage.h"
#include "intro.h"
#include "scene1.h"
#include "scene2.h"
#include "scene3.h"
#include "scene4.h"
#include "scene5.h"
#include "ending.h"
#include "intro_animation.h"
#include "scene1_animation.h"
#include "scene2_animation.h"
#include "scene3_animation.h"
#include "scene4_animation.h"
#include "scene5_animation.h"
#include "ending_animation.h"



void init(void)
{
	glClearColor(0.0, 0.0, 0.0, 0.0);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluOrtho2D(0, 600, 0, 500);
	glMatrixMode(GL_MODELVIEW);

}

void Timer(int value) {
	if (value)glutPostRedisplay();
	glutTimerFunc(500, Timer, value);
}

void renderSubtitle(float x, float y, const char* text, float r, float g, float b, void* font, int fontSize)
{
	glColor3f(r, g, b);
	glRasterPos2f(x, y);

	for (int i = 0; text[i] != '\0'; i++)
	{
		glutBitmapCharacter(font, text[i]);
	}
}

void animate() { 

	
    if (scene_counter == 1) 
		intro_animation();
	else if (scene_counter == 2)
		scene1_animation();
	else if (scene_counter == 3)
		scene2_animation();
	else if (scene_counter == 4)
		scene3_animation();
	else if (scene_counter == 5)
		scene4_animation();
	else if (scene_counter == 6)
		scene5_animation();
	else if (scene_counter == 7)
		ending_animation();
}

void scene_idle() { 
	if (scene_counter == 0)
		displayfront();
    if (scene_counter == 1)
		intro_idle();
	if (scene_counter == 2)
		scene1_idle();
	if (scene_counter == 3)
		scene2_idle();
	if (scene_counter == 4)
		scene3_idle();
	if (scene_counter == 5)
		scene4_idle();
	if (scene_counter == 6)
		scene5_idle();
	if (scene_counter == 7)
		ending_idle();
}

void display(void)
{
	glClear(GL_COLOR_BUFFER_BIT);
	glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);  //

	////Animation happens here

	frame++;
	if (isRunning) {
		scene_idle();
	}
	if (frame == 0)
	{
		init();
	}

	printf("%d\n", frame);
	if (frame < 0 && start)
	{
		scene_counter = 0;
	}
	else if (frame >= 0 && frame <30) {scene_counter = 1;}  //intro
	else if (frame >= 30 && frame < 2600) { scene_counter = 2; }  //dino scene first
	else if (frame >= 2600 && frame < 5200) { scene_counter = 3; }  //man scene first
	else if (frame >= 5200 && frame < 6600) { scene_counter = 4; }  //man dino meet
	else if (frame >= 6600 && frame < 7400) { scene_counter = 5; }  //man dino come close
	else if (frame >= 7400 && frame < 11000) { scene_counter = 6; }  //they meet
	else if (frame >= 11000 && frame < 15300) { scene_counter = 7; }  //end scene

	if (scene_counter == 1) {
		intro();
		renderSubtitle(150, 300, "Trailer", 0.0, 0.0, 0.0, GLUT_BITMAP_TIMES_ROMAN_24, 24);
		renderSubtitle(150, 130, "Directed By:", 0.0, 0.0, 0.0, GLUT_BITMAP_TIMES_ROMAN_24, 24);
		renderSubtitle(150, 110, "Vithesh and Pavan", 0.0, 0.0, 0.0, GLUT_BITMAP_TIMES_ROMAN_24, 24);
	}
	else if (scene_counter == 2) {
		scene1();
		glColor3f(0.0, 0.0, 0.0);
		renderSubtitle(200, 150, "A hungry T-rex looking for food in order to survive.", 1.0, 0.0, 0.0, GLUT_BITMAP_HELVETICA_18, 18);
		renderSubtitle(200, 130, "However, the very hungry t-rex cannot find any food nearby", 1.0, 0.0, 0.0, GLUT_BITMAP_HELVETICA_18, 18);
		renderSubtitle(200, 110, "so he keeps looking.", 1.0, 0.0, 0.0, GLUT_BITMAP_HELVETICA_18, 18);
	}		
	else if (scene_counter == 3) {
				scene2();
				glViewport(0, 0, 1300, 800);
				glColor3f(0.0, 0.0, 0.0);
				renderSubtitle(200, 150, "He is Pavan. A happy 18-year-old boy who loves adventures.", 1.0, 0.0, 0.0, GLUT_BITMAP_HELVETICA_18, 18);
				renderSubtitle(200, 130, "He once decided to go out of his city because people said there where dinasaurs outside the city", 1.0, 0.0, 0.0, GLUT_BITMAP_HELVETICA_18, 18);
				renderSubtitle(200, 110, "Dave didn't believe this and decided to check it out...", 1.0, 0.0, 0.0, GLUT_BITMAP_HELVETICA_18, 18);
			}
	else if (scene_counter == 4) {
				scene3();
				glViewport(0, 0, 1300, 800);
				glColor3f(0.0, 0.0, 0.0);
				renderSubtitle(200, 150, "Until Pavan reached the desert...", 1.0, 0.0, 0.0, GLUT_BITMAP_HELVETICA_18, 18);
			}
	else if (scene_counter == 5) {
				scene4();
				glViewport(0, 0, 1300, 800);
				glColor3f(0.0, 0.0, 0.0);
				renderSubtitle(200, 150, "He encounters a hungry t-rex coming closer to him and...", 1.0, 0.0, 0.0, GLUT_BITMAP_HELVETICA_18, 18);
			}
	else if (scene_counter == 6) {
				scene5();
				glViewport(0, 0, 1300, 800);
				glColor3f(1.0, 1.0, 1.0);
				renderSubtitle(200, 150, "At that moment, Pavan was shocked as he thought T-rex didn't exist", 0.0, 0.0, 0.0, GLUT_BITMAP_HELVETICA_18, 18);
				renderSubtitle(200, 130, "and the T-rex became very happy and craving to eat Dave.", 0.0, 0.0, 0.0, GLUT_BITMAP_HELVETICA_18, 18);
				renderSubtitle(200, 110, "WHAT WILL PAVAN+ DO NOW!!??......", 0.0, 0.0, 0.0, GLUT_BITMAP_HELVETICA_18, 18);
			}
	else if (scene_counter == 7) {

		ending();
		glColor3f(1.0, 1.0, 1.0);
		renderSubtitle(100, 300, "Will Pavan Survive ???", 0.0, 0.0, 0.0, GLUT_BITMAP_HELVETICA_18, 18);
		renderSubtitle(100, 280, "Watch The Hungry T-Rex in full.", 0.0, 0.0, 0.0, GLUT_BITMAP_HELVETICA_18, 18);
		renderSubtitle(100, 250, "Coming on August 31, 2023 at Sahyadri.", 0.0, 0.0, 0.0, GLUT_BITMAP_HELVETICA_18, 18);
		renderSubtitle(100, 220, "Book your tickets now!!.", 0.0, 0.0, 0.0, GLUT_BITMAP_HELVETICA_18, 18);
	}








	animate();
	if (frame > 15300 ) {
		frame = 0;
		isRunning = false;
		exit(0);
	}
	glutSwapBuffers();
	glFlush();
}


void Visibility(GLint state) {
	switch (state) {
	case GLUT_VISIBLE:
		Timer(1);
		break;
	case GLUT_NOT_VISIBLE:
		Timer(0);
		break;
	default:
		break;
	}
}


int main(int argc, char** argv)
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
	glutInitWindowSize(1300, 800);
	glutInitWindowPosition(100, 100);
	glutCreateWindow("The Hungry T-Rex the Movie Trailer");
	init();
	glutDisplayFunc(display);
	glutTimerFunc(0, Timer, 0);
	glutVisibilityFunc(Visibility);
	glutMainLoop();
	return 0;
}



