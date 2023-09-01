#include <string.h>

int start = 1;

//frame counter
int frame = -15, scene_counter = 1;

//sun scene 1
float scale_sunx = 1, scale_suny = 1, sun_count;

//cloud left and right scene 1
float slide_cloudL, slide_cloudR, move_cloud, count_cloud;

//dino animation scene 1
float dino_movex, dino_movey, dino_count, dino_slidex, dino_slidey, dinoR_lost, dinoL_lost;

//dino legs scene 1
float dinolegL_angle = 0, dinolegR_angle = 0, dinoleg_angle_move, dinoleg_move_x, dinoleg_move_y, dinoleg_count = 0, dinoR_leg_lost, dinoL_leg_lost;

//man leg scene 2
float manlegL_angle = 0, manlegR_angle = 0, manleg_angle_move, manleg_move_x, manleg_move_y, manleg_count = 0, manR_leg_lost, manL_leg_lost;

//man arms scene 2
float manarmL_angle = 0, manarmR_angle = 0, manarm_angle_move, manarm_move_x, manarm_move_y, manarm_count = 0, manR_arm_lost, manL_arm_lost;

//man animation scene 2
float man_movex, man_movey, man_count = 0, man_slidex, man_slidey, manR_lost, manL_lost;

//dino animation for scene 3
float dinoRight_movex, dinoRight_movey, dinoRight_count, dinoRight_slidex, dinoRight_slidey, dinoRRight_lost, dinoLRight_lost;

//dino legs for scene 3
float dinolegRightL_angle = 0, dinolegRightR_angle = 0, dinoRightleg_angle_move, dinoRightleg_move_x, dinoRightleg_move_y, dinoRightleg_count = 0, dinoRightR_leg_lost, dinoRightL_leg_lost;

//man leg for scene 3
float manRightLegL_angle = 0, manRightLegR_angle = 0, manRightLeg_angle_move, manRightLeg_move_x, manRightLeg_move_y, manRightLeg_count = 0, manRightR_leg_lost, manRightL_leg_lost;

//man arms for scene 3
float manRightarmL_angle = 0, manRightarmR_angle = 0, manRightarm_angle_move, manRightarm_move_x, manRightarm_move_y, manRightarm_count = 0, manRightR_arm_lost, manRightL_arm_lost;

//man animation for scene 3
float manRight_movex, manRight_movey, manRight_count = 0, manRight_slidex, manRight_slidey, manRightR_lost, manRightL_lost;

//dino for scene 4
float dinoRight_movex4, dinoRight_movey4, dinoRight_count4, dinoRight_slidex4, dinoRight_slidey4, dinoRRight_lost4, dinoLRight_lost4;

//dino legs for scene 4
float dinolegRightL_angle4 = 0, dinolegRightR_angle4 = 0, dinoRightleg_angle_move4, dinoRightleg_move_x4, dinoRightleg_move_y4, dinoRightleg_count4 = 0, dinoRightR_leg_lost4, dinoRightL_leg_lost4;

//man leg for scene 4
float manRightLegL_angle4 = 0, manRightLegR_angle4 = 0, manRightLeg_angle_move4, manRightLeg_move_x4, manRightLeg_move_y4, manRightLeg_count4 = 0, manRightR_leg_lost4, manRightL_leg_lost4;

//man arms for scene 4
float manRightarmL_angle4 = 0, manRightarmR_angle4 = 0, manRightarm_angle_move4, manRightarm_move_x4, manRightarm_move_y4, manRightarm_count4 = 0, manRightR_arm_lost4, manRightL_arm_lost4;

//man animation for scene 4
float manRight_movex4, manRight_movey4, manRight_count4 = 0, manRight_slidex4, manRight_slidey4, manRightR_lost4, manRightL_lost4;

float fadex, fademove;

//man zoom animation scene 5
float manScalex = 1.0, manScaley = 1.0, moveScaleMan, scaleManCount = 0, moveScaleManLost;
//dino zoom animation scene 5
float dinoScalex = 1.0, dinoScaley = 1.0, moveScaleDino, scaleDinoCount = 0, moveScaleDinoLost;

//ending animation
//dino animation scene 1
float dino_movex5, dino_movey5, dino_count5, dino_slidex5 = -400, dino_slidey5=-50, dinoR_lost5, dinoL_lost5;

float man_movex5, man_movey5, man_count5 = 0, man_slidex5, man_slidey5, manR_lost5, manL_lost5;

//dino legs scene 1
float dinolegL_angle5 = 0, dinolegR_angle5 = 0, dinoleg_angle_move5, dinoleg_move_x5, dinoleg_move_y5, dinoleg_count5 = 0, dinoR_leg_lost5, dinoL_leg_lost5;

bool isRunning = true;






//****************************************************************************



//dino animation scene 2
float sdino_movex, sdino_movey, sdino_count, sdino_slidex, sdino_slidey, sdinoR_lost, sdinoL_lost;

//dino legs scene 2
float sdinolegL_angle = 0, sdinolegR_angle = 0, sdinoleg_angle_move, sdinoleg_move_x, sdinoleg_move_y, sdinoleg_count = 0, sdinoR_leg_lost, sdinoL_leg_lost;



//*****************************************************************************

void testPrint(float f1, float f2) {
	printf("%f, %f\n",f1,f2);
}