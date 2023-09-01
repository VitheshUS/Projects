
// Function to draw a rectangle
void drawRectangle1(int x1, int y1, int x2, int y2, float r, float g, float b) {
    glColor3f(r, g, b);
    glBegin(GL_QUADS);
    glVertex2i(x1, y1);
    glVertex2i(x2, y1);
    glVertex2i(x2, y2);
    glVertex2i(x1, y2);
    glEnd();
}

void drawCircle1(int centerX, int centerY, int radius, float r, float g, float b) {
    glColor3f(r, g, b);
    glBegin(GL_TRIANGLE_FAN);
    glVertex2f(centerX, centerY);
    for (int i = 0; i <= 360; i++) {
        float angle = i * 3.1415926f / 180;
        float x = centerX + radius * cosf(angle);
        float y = centerY + radius * sinf(angle);
        glVertex2f(x, y);
    }
    glEnd();
}

// Function to handle drawing
void drawScene1() {
    glClear(GL_COLOR_BUFFER_BIT);

    // Draw the sky
    drawRectangle1(0, 200, 600, 500, 0.529, 0.808, 0.922); // Light blue color

    // Draw the sun
    drawCircle(100, 400, 50, 1.0, 1.0, 0.0); // Yellow color

    // Draw the ground
    drawRectangle1(0, 0, 600, 260, 0.855, 0.647, 0.125); // Sand color

    // Draw dunes
    glColor3f(0.961, 0.871, 0.702); // Light sand color
    glBegin(GL_TRIANGLES);
    glVertex2i(0, 250);
    glVertex2i(300, 500);
    glVertex2i(600, 250);
    glEnd();

    // Draw cacti
    glColor3f(0.0, 0.502, 0.0); // Green color
    for (int i = 400; i < 600; i += 200) {
        glBegin(GL_TRIANGLES);
        glVertex2i(i, 200);
        glVertex2i(i + 20, 200);
        glVertex2i(i + 10, 350);
        glEnd();
        
    }

 

    glFlush();
}
void ending() {
    drawScene1();
}
