// Function to draw a rectangle
void drawRectangle(int x1, int y1, int x2, int y2, float r, float g, float b) {
    glColor3f(r, g, b);
    glBegin(GL_QUADS);
    glVertex2i(x1, y1);
    glVertex2i(x2, y1);
    glVertex2i(x2, y2);
    glVertex2i(x1, y2);
    glEnd();
}

// Function to draw a circle
void drawCircle(int centerX, int centerY, int radius, float r, float g, float b) {
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

// Function to draw a building
void drawBuilding(int x, int y, int width, int height, float r, float g, float b) {
    drawRectangle(x, y, x + width, y + height, r, g, b);
    int numWindows = 6;
    int windowWidth = width / (numWindows + 2);
    int windowHeight = height / (numWindows / 2 + 2);
    int windowX = x + windowWidth;
    int windowY = y + windowHeight;
    for (int i = 0; i < numWindows / 2; i++) {
        for (int j = 0; j < numWindows / 2; j++) {
            drawRectangle(windowX + j * (windowWidth + 10), windowY + i * (windowHeight + 10),
                windowX + j * (windowWidth + 10) + windowWidth,
                windowY + i * (windowHeight + 10) + windowHeight,
                0.729, 0.831, 0.929); // Window color
        }
    }
}

// Function to handle drawing
void drawScene() {
    glClear(GL_COLOR_BUFFER_BIT);

    // Draw the sky
    drawRectangle(0, 200, 600, 500, 0.529, 0.808, 0.922); // Light blue color

    // Draw the sun
    drawCircle(100, 400, 50, 1.0, 1.0, 0.0); // Yellow color

    // Draw the ground
    drawRectangle(0, 0, 600, 250, 0.196, 0.804, 0.196); // Green color

    // Add more details to the ground
    for (int i = 0; i < 600; i += 20) {
        drawRectangle(i, 0, i + 10, 10, 0.502, 0.502, 0.502); // Gray lines
    }

    // Draw the buildings
    drawBuilding(50, 200, 80, 400, 0.502, 0.502, 0.502);   // Gray building
    drawBuilding(200, 250, 100, 350, 0.678, 0.847, 0.902); // Light blue building
    drawBuilding(350, 200, 120, 400, 0.502, 0.502, 0.502); // Gray building
    drawBuilding(500, 250, 150, 350, 0.729, 0.831, 0.929); // Blue building

    glFlush();
}


void scene2() {
    drawScene();
	
}






