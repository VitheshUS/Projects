void ending_dino_idle() {
	//idle
	if (dino_slidey5 > 0) dino_movey5 = 1;
	if (dino_movey5 == 1) dino_slidey5 -= 0.15; // Decreased the increment value to slow down the movement
	if (dino_slidey5 < -20) dino_movey5 = 0;
	if (dino_movey5 == 0) dino_slidey5 += 0.15; // Decreased the increment value to slow down the movement

	if (dino_slidex5 < 1000) { dino_movex5 = 0; dino_count5 = 0; dinoleg_count5 = 0; }
	//if (dino_movex5 == 1) dino_slidex5 -= 1;//4
	//if (dino_slidex5 < -1600) dino_movex5 = 0;
	if (dino_movex5 == 0) dino_slidex5 += 0.5; // Decreased the increment value to slow down the movement

	if (dino_count5 == 0) {
		dinoR_lost5 = 1; dinoL_lost5 = 0;
	}
	else {
		dinoR_lost5 = 0; dinoL_lost5 = 0;
	}
	glutPostRedisplay();
}

void ending_dinoLleg_idle() {
	if (dinolegL_angle5 > 30)  dinoleg_angle_move5 = 0;
	if (dinoleg_angle_move5 == 0)  dinolegL_angle5 -= 0.25; // Decreased the increment value to slow down the rotation
	if (dinolegL_angle5 < -30)  dinoleg_angle_move5 = 1;
	if (dinoleg_angle_move5 == 1)  dinolegL_angle5 += 0.25; // Decreased the increment value to slow down the rotation

	if (dinoleg_count5 == 0) {
		dinoR_leg_lost5 = 1; dinoL_leg_lost5 = 0;
	}
	else {
		dinoR_leg_lost5 = 0; dinoL_leg_lost5 = 0;
	}
	glutPostRedisplay();
}

void ending_dinoRleg_idle() {
	if (dinolegR_angle5 > 30)  dinoleg_angle_move5 = 1;
	if (dinoleg_angle_move5 == 0)  dinolegR_angle5 += 0.25; // Decreased the increment value to slow down the rotation
	if (dinolegR_angle5 < -30)  dinoleg_angle_move5 = 0;
	if (dinoleg_angle_move5 == 1)  dinolegR_angle5 -= 0.25; // Decreased the increment value to slow down the rotation

	if (dinoleg_count5 == 0) {
		dinoR_leg_lost5 = 1; dinoL_leg_lost5 = 0;
	}
	else {
		dinoR_leg_lost5 = 0; dinoL_leg_lost5 = 0;
	}
	glutPostRedisplay();
}



void ending_dino_animation() {
	//moving to the right
	glPushMatrix();
	if (dino_count5 == 0)
		glTranslatef(dino_slidex5, dino_slidey5, 0);
	glTranslatef(0, 125, 0);
	glScalef(0.6, 0.6, 1);

	glScalef(dinoR_lost5, dinoR_lost5, 0);
	dinoLeft_Tail();
	dinoLeft_RightArm();
	dinoLeft_Body();
	dinoLeft_HeadAndNeck();
	dinoLeft_LeftArm();
	glPopMatrix();
}

void ending_dinoLleg_animation() {
	glPushMatrix();
	if (dinoleg_count5 == 0) {
		glTranslatef(dino_slidex5 - 80, dino_slidey5 + 10, 0);
		glTranslatef(200, 290, 0);
		glScalef(0.6, 0.6, 1);
		glRotatef(dinolegL_angle5, 0, 0, 1);
		glTranslatef(-200, -290, 0);
	}
	//pupil

	glScalef(dinoR_leg_lost5, dinoR_leg_lost5, 0);
	dinoLeft_LegLeft();
	glPopMatrix();
}

void ending_dinoRleg_animation() {
	glPushMatrix();
	if (dinoleg_count5 == 0) {
		glTranslatef(dino_slidex5 - 80, dino_slidey5 + 10, 0);
		glTranslatef(200, 290, 0);
		glScalef(0.6, 0.6, 1);
		glRotatef(dinolegR_angle5, 0, 0, 1);
		glTranslatef(-200, -290, 0);
	}
	glScalef(dinoR_leg_lost5, dinoR_leg_lost5, 0);
	dinoLeft_LegRight();
	glPopMatrix();
}


void ending_man_idle() {
	if (man_slidex5 < 1000) {
		man_movex5 = 0; man_count5 = 0; manleg_count = 0; manarm_count = 0;
	}

	if (man_movex5 == 0) man_slidex5 += 0.5; // Decreased the increment value to slow down the movement

	if (man_count5 == 0) {
		manR_lost = 1; manL_lost = 0;
	}
	else {
		manR_lost = 0; manL_lost = 0;
	}
	glutPostRedisplay();
}

void ending_manleftleg_idle() {
	if (manlegL_angle > 30)  manleg_angle_move = 0;
	if (manleg_angle_move == 0)  manlegL_angle -= 0.7; // Decreased the increment value to slow down the rotation
	if (manlegL_angle < -30)  manleg_angle_move = 1;
	if (manleg_angle_move == 1)  manlegL_angle += 0.7; // Decreased the increment value to slow down the rotation

	if (manleg_count == 0) {
		manR_leg_lost = 1; manL_leg_lost = 0;
	}
	else {
		manR_leg_lost = 0; manL_leg_lost = 0;
	}
	glutPostRedisplay();
}

void ending_manrightleg_idle() {
	if (manlegR_angle > 30)  manleg_angle_move = 1;
	if (manleg_angle_move == 1)  manlegR_angle -= 0.7; // Decreased the increment value to slow down the rotation
	if (manlegR_angle < -30)  manleg_angle_move = 0;
	if (manleg_angle_move == 0)  manlegR_angle += 0.7; // Decreased the increment value to slow down the rotation

	if (manleg_count == 0) {
		manR_leg_lost = 1; manL_leg_lost = 0;
	}
	else {
		manR_leg_lost = 0; manL_leg_lost = 0;
	}
	glutPostRedisplay();
}

void ending_manrightarm_idle() {
	if (manarmR_angle > 30) manarm_angle_move = 1;
	if (manarm_angle_move == 0) manarmR_angle += 0.7; // Decreased the increment value to slow down the rotation
	if (manarmR_angle < -30) manarm_angle_move = 0;
	if (manarm_angle_move == 1) manarmR_angle -= 0.7; // Decreased the increment value to slow down the rotation

	if (manarm_count == 0) { manR_arm_lost = 1; manL_arm_lost = 0; }
	else { manR_arm_lost = 0; manL_arm_lost = 0; }
	glutPostRedisplay();
}

void ending_manleftarm_idle() {
	if (manarmL_angle > 30) manarm_angle_move = 0;
	if (manarm_angle_move == 0) manarmL_angle -= 0.7; // Decreased the increment value toslow down the rotation
	if (manarmL_angle < -30) manarm_angle_move = 1;
	if (manarm_angle_move == 1) manarmL_angle += 0.7; // Decreased the increment value to slow down the rotation

	if (manarm_count == 0) { manR_arm_lost = 1; manL_arm_lost = 0; }
	else { manR_arm_lost = 0; manL_arm_lost = 0; }
	glutPostRedisplay();
}





void ending_man_animation() {
	//moving to the right
	glPushMatrix();
	if (man_count == 0)
		glTranslatef(man_slidex5, man_slidey, 0);
	glTranslatef(-40, 155, 0);
	glScalef(0.4, 0.4, 1);
	glScalef(manR_lost, manR_lost, 0);
	man_body();
	man_head();
	glPopMatrix();
}

void ending_manLleg_animation() {
	glPushMatrix();
	if (manleg_count == 0) {
		glTranslatef(man_slidex5 - 225, man_slidey + 40, 0);
		glTranslatef(310, 180, 0);
		glScalef(0.4, 0.4, 1);
		glRotatef(manlegL_angle, 0.0, 0.0, 1);
		glTranslatef(-310, -180, 0);
	}

	glScalef(manR_leg_lost, manR_leg_lost, 0);
	man_leftLeg();
	glPopMatrix();
}

void ending_manRleg_animation() {
	glPushMatrix();
	if (manleg_count == 0) {
		glTranslatef(man_slidex5 - 220, man_slidey + 40, 0);
		glTranslatef(310, 180, 0);
		glScalef(0.4, 0.4, 1);
		glRotatef(manlegR_angle, 0, 0, 1);
		glTranslatef(-310, -180, 0);
	}
	glScalef(manR_leg_lost, manR_leg_lost, 0);
	man_rightLeg();
	glPopMatrix();
}

void ending_manRarm_animation() {
	glPushMatrix();
	if (manarm_count == 0) {
		glTranslatef(man_slidex5 - 208, man_slidey - 10, 0);
		glTranslatef(280, 270, 0);
		glScalef(0.4, 0.4, 1);
		glRotatef(manarmR_angle, 0, 0, 1);
		glTranslatef(-280, -270, 0);
	}
	glScalef(manR_arm_lost, manR_arm_lost, 0);
	man_rightArm();
	glPopMatrix();
}

void ending_manLarm_animation() {
	glPushMatrix();
	if (manarm_count == 0) {
		glTranslatef(man_slidex5 - 208, man_slidey - 10, 0);
		glTranslatef(280, 270, 0);
		glScalef(0.4, 0.4, 1);
		glRotatef(manarmL_angle, 0, 0, 1);
		glTranslatef(-280, -270, 0);
	}
	glScalef(manR_arm_lost, manR_arm_lost, 0);
	man_leftArm();
	glPopMatrix();
}



void ending_idle() {
	ending_dino_idle();
	ending_dinoRleg_idle();
	ending_dinoLleg_idle();

	ending_man_idle();
	ending_manleftarm_idle();
	ending_manrightarm_idle();
	ending_manrightleg_idle();
	ending_manleftleg_idle();
}

void ending_animation() {
	ending_dinoRleg_animation();
	ending_dino_animation();
	ending_dinoLleg_animation();

	ending_manLarm_animation();
	ending_manLleg_animation();
	ending_manRleg_animation();
	ending_man_animation();
	ending_manRarm_animation();
}