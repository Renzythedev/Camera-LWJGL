import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class Camera {

    public static enum CameraState {
        ORTHOGRAPICH,
        CAMERA;
    }

    public static enum MatrixState {
        MATRIX,
        PERSECTIVE;
    }

    public static enum Options {
        MATRIX,
        TRANSLATIONS;
    }

    private CameraState cameraState;
    private MatrixState matrixState;
    private Options options;
    private float x,y,z;
    private float pitch,yaw,roll;
    private float fov;
    private float aspectRatio;
    private float zNear,zFar;

    private int right,left,top,bottom;


    public Camera(CameraState state) {
        switch (state) {
            case ORTHOGRAPICH -> {cameraState=state; matrixState = MatrixState.MATRIX; break;}
            case CAMERA -> {cameraState=state; matrixState = MatrixState.PERSECTIVE; break;}
        }
    }

    public void set(Options option) {
        switch (option) {
            case MATRIX:
                switch (matrixState) {
                    case MATRIX -> {GL11.glOrtho(left,right,bottom,top,zNear,zFar); break;}
                    case PERSECTIVE -> {GLU.gluPerspective(fov,aspectRatio,zNear,zFar); break;}
                }
                break;
            case TRANSLATIONS:
                if(cameraState==CameraState.CAMERA) {
                    GL11.glRotatef(pitch,1,0,0);
                    GL11.glRotatef(yaw,0,1,0);
                    GL11.glRotatef(roll,0,0,1);
                    GL11.glTranslatef(-x,-y,-z);
                }
                break;
        }
    }

    public void cameraEvent(float sensitivity) {
        final float maxLoopUp=85;
        final float maxLoopDown=-85;
        float dx= Mouse.getDX() * sensitivity * 0.16f;
        float dy= Mouse.getDY() * sensitivity * 0.16f;
        if(yaw + dx >= 360) {
            yaw = yaw + dx - 360;
        }else if (yaw + dx < 0) {
            yaw = 360 - yaw + dx;
        }else {
            yaw+=dx;
        }

        if(pitch - dy >= maxLoopDown && pitch - dy <= maxLoopUp) {
            pitch += -dy;
        }else if(pitch - dy < maxLoopDown) {
            pitch = maxLoopDown;
        }else if(pitch - dy > maxLoopUp) {
            pitch = maxLoopUp;
        }
    }

    public void cameraEvent(float sensitivity, float maxLookUp,float maxLookDown) {
        float dx= Mouse.getDX() * sensitivity * 0.16f;
        float dy= Mouse.getDY() * sensitivity * 0.16f;
        if(yaw + dx >= 360) {
            yaw = yaw + dx - 360;
        }else if (yaw + dx < 0) {
            yaw = 360 - yaw + dx;
        }else {
            yaw+=dx;
        }

        if(pitch - dy >= maxLookDown && pitch - dy <= maxLookUp) {
            pitch += -dy;
        }else if(pitch - dy < maxLookDown) {
            pitch = maxLookDown;
        }else if(pitch - dy > maxLookUp) {
            pitch = maxLookUp;
        }
    }

    public void moveEvent(float delta, float speedx,float speedy,float speedz) {
        float i = 0.003f;
        boolean keyUp = Keyboard.isKeyDown(Keyboard.KEY_W);
        boolean keyDown = Keyboard.isKeyDown(Keyboard.KEY_S);
        boolean keyRight = Keyboard.isKeyDown(Keyboard.KEY_D);
        boolean keyLeft = Keyboard.isKeyDown(Keyboard.KEY_A);
        boolean flyUp = Keyboard.isKeyDown(Keyboard.KEY_SPACE);
        boolean flyDown= Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);

        if(keyUp && !keyDown && !keyLeft && !keyRight) {
            move(0,0,-speedz * delta * i);
        }
        if(keyDown && !keyUp && !keyLeft && !keyRight) {
            move(0,0, speedz *delta*i);
        }
        if(keyLeft && !keyRight && !keyDown && !keyUp) {
            move(-speedx * delta * i, 0,0);
        }
        if(keyRight && !keyLeft && !keyDown && !keyUp) {
            move(speedx * delta * i, 0,0);
        }
        if(keyUp && keyLeft && !keyRight && !keyDown) {
            move(-speedx * delta * i, 0 ,-speedz*delta*i);
        }
        if(keyUp &&keyRight && !keyDown && !keyLeft) {
            move(speedx * delta * i, 0, -speedz*delta*i);
        }
        if(keyDown && keyLeft &&!keyRight &&!keyUp){
            move(-speedx * delta * i, 0 ,speedz*delta*i);
        }
        if(keyDown && keyRight && !keyLeft && !keyUp) {
            move(speedx * delta * i, 0 ,speedz*delta*i);
        }
        if(flyUp && !flyDown) {
            y += speedy *delta *i;
        }
        if(flyDown && !flyUp) {
            y -= speedy * delta *i;
        }
    }

    public void move(float dx,float dy,float dz) {
        float usX = dx;
        float adX = usX* (float) Math.cos(Math.toRadians(yaw - 90));
        float opX = usX* (float) Math.sin(Math.toRadians(yaw - 90));
        this.x-=opX;
        this.z+=adX;

        this.y+=dy;

        float usZ = dz;
        float adZ = usZ* (float) Math.cos(Math.toRadians(yaw));
        float opZ = usZ* (float) Math.sin(Math.toRadians(yaw));
        this.x-=opZ;
        this.z+=adZ;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getRoll() {
        return roll;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }

    public float getFov() {
        return fov;
    }

    public void setFieldOfView(float fov) {
        this.fov = fov;
    }

    public float getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(float aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public float getzNear() {
        return zNear;
    }

    public void setzNear(float zNear) {
        this.zNear = zNear;
    }

    public float getzFar() {
        return zFar;
    }

    public void setzFar(float zFar) {
        this.zFar = zFar;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }
}
