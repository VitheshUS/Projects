
void sdinoLeft_LeftArm() {
	glColor3f(0,0.3,0);
	drawEllipse(5, 5, 130, 125);
	glBegin(GL_POLYGON);
	glVertex2f(130, 125);
	glVertex2f(135, 125);
	glVertex2f(140, 120);
	glVertex2f(145, 120);
	glVertex2f(145, 115);
	glVertex2f(135, 115);
	glVertex2f(130, 120);
	glVertex2f(130, 125);
	glEnd();

	drawEllipse(5, 5, 145, 117);

	glBegin(GL_POLYGON);
	glColor3f(0, 0.3, 0);
	glVertex2f(147.5, 115);
	glVertex2f(150, 112.5);
	glVertex2f(147.5, 110);
	glVertex2f(147.5, 112.5);
	glVertex2f(145, 112.5);
	glVertex2f(145, 115);
	glEnd();

	glBegin(GL_POLYGON);
	glColor3f(0,0.3,0);
	glVertex2f(150, 117.5);
	glVertex2f(152.5, 115);
	glVertex2f(150, 112.5);
	glVertex2f(150, 115);
	glVertex2f(147.5, 115);
	glVertex2f(147.5, 117.5);
	glEnd();
}


void sdinoLeft_RightArm() {
	glColor3f(0.0, 0.0, 0);
	drawEllipse(5, 5, 135, 125);
	glBegin(GL_POLYGON);
	glVertex2f(135, 125);
	glVertex2f(140, 125);
	glVertex2f(145, 120);
	glVertex2f(150, 120);
	glVertex2f(150, 115);
	glVertex2f(140, 115);
	glVertex2f(135, 120);
	glVertex2f(135, 125);
	glEnd();

	drawEllipse(5, 5, 150, 117);

	glBegin(GL_POLYGON);
	glColor3f(0.0, 0.0, 0);
	glVertex2f(152.5, 115);
	glVertex2f(155, 112.5);
	glVertex2f(152.5, 110);
	glVertex2f(152.5, 112.5);
	glVertex2f(150, 112.5);
	glVertex2f(150, 115);
	glEnd();

	glBegin(GL_POLYGON);
	glColor3f(0.0, 0.0, 0);
	glVertex2f(155, 117.5);
	glVertex2f(157.5, 115);
	glVertex2f(155, 112.5);
	glVertex2f(155, 115);
	glVertex2f(152.5, 115);
	glVertex2f(152.5, 117.5);
	glEnd();
}


void sdinoLeft_HeadAndNeck() {
	glBegin(GL_POLYGON);
	glColor3f(0, 0.4, 0);
	glVertex2f(150, 175);
	glVertex2f(175, 170);
	glVertex2f(180, 165);
	glVertex2f(180, 160);
	glVertex2f(175, 160);
	glVertex2f(160, 155);
	glVertex2f(170, 155);
	glVertex2f(180, 155);
	glVertex2f(175, 150);
	glVertex2f(180, 155);
	glVertex2f(155, 145);
	glVertex2f(150, 150);
	glVertex2f(150, 145);
	glVertex2f(135, 122.5);
	glVertex2f(135, 150);
	glVertex2f(150, 175);
	glEnd();

	drawEllipse(5, 15, 135, 135);

	glColor3f(255.0, 255.0, 255.0);
	drawCircle(2.5, 155, 170);

	glPointSize(2.5);
	glBegin(GL_POINTS);
	glColor3f(0.0, 0.0, 0.0);
	glVertex2f(155, 170);
	glEnd();
}


void sdinoLeft_Body() {
	glBegin(GL_POLYGON);
	glColor3f(0.0, 0.2, 0.0);

	glVertex2f(135, 150);
	glVertex2f(135, 120);
	glVertex2f(125, 115);
	glVertex2f(80, 125);
	glVertex2f(90, 155);
	glVertex2f(130, 155);
	glVertex2f(135, 150);
	glEnd();
}

void sdinoLeft_Tail() {
	glBegin(GL_POLYGON);
	glColor3f(0, 0.2, 0);
	glVertex2f(15, 150);
	glVertex2f(75, 150);
	glVertex2f(90, 155);
	glVertex2f(80, 125);
	glVertex2f(75, 125);
	glVertex2f(75, 135);
	glVertex2f(15, 150);
	glEnd();
}

void sdinoLeft_LegLeft() {
	glColor3f(0, 0.4, 0);
	drawEllipse(15, 15, 97.5, 125);

	glBegin(GL_POLYGON);
	//glColor3f(0.0, 0.3, 0);
	glVertex2f(112.5, 122.5);
	glVertex2f(100, 100);
	glVertex2f(85, 100);
	glVertex2f(95, 122.5);
	glVertex2f(90, 90);
	glVertex2f(85, 90);
	glVertex2f(85, 80);
	glVertex2f(85, 100);
	glEnd();

	drawEllipse(7.5, 5, 92.5, 82.5);

	//Feet Claws
	glBegin(GL_QUADS);
	glColor3f(0.0, 0.0, 0);
	glVertex2f(100, 80);
	glVertex2f(97.5, 82.5);
	glVertex2f(95, 82.5);
	glVertex2f(95, 80);
	glVertex2f(100, 80);
	glEnd();

	glBegin(GL_QUADS);
	glColor3f(0.0, 0.0, 0);
	glVertex2f(102.5, 82.5);
	glVertex2f(100, 85);
	glVertex2f(97.5, 85);
	glVertex2f(97.5, 82.5);
	glVertex2f(102.5, 82.5);
	glEnd();
}


void sdinoLeft_LegRight() {
	glColor3f(0.0, 0.0, 0.0);
	drawEllipse(15, 15, 102.5, 125);

	glBegin(GL_POLYGON);
	//glColor3f(0.0, 0.0, 0);
	glVertex2f(117.5, 122.5);
	glVertex2f(105, 100);
	glVertex2f(90, 100);
	glVertex2f(100, 122.5);
	glVertex2f(95, 90);
	glVertex2f(90, 90);
	glVertex2f(90, 80);
	glVertex2f(90, 100);
	glVertex2f(117.5, 122.5);
	glEnd();

	drawEllipse(7.5, 5, 97.5, 82.5);

	//Feet Claws
	glBegin(GL_QUADS);
	glColor3f(0.0, 0.0, 0);
	glVertex2f(105, 80);
	glVertex2f(102.5, 82.5);
	glVertex2f(100, 82.5);
	glVertex2f(100, 80);
	glVertex2f(105, 80);
	glEnd();

	glBegin(GL_QUADS);
	glColor3f(0.0, 0.0, 0);
	glVertex2f(107.5, 82.5);
	glVertex2f(105, 85);
	glVertex2f(100, 85);
	glVertex2f(100, 82.5);
	glVertex2f(107.5, 82.5);
	glEnd();
}



void sdinoLeft() {
	printf("\n enter \n");
	glTranslatef(200,0,0);
	glScalef(1.5,1.5,0);
	sdinoLeft_Tail();
	sdinoLeft_LegRight();
	sdinoLeft_RightArm();glColor3f(0.0, 0.2, 0.0);
	sdinoLeft_Body();
	sdinoLeft_HeadAndNeck();
	sdinoLeft_LegLeft();
	sdinoLeft_LeftArm();

}