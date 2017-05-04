
# Shader 与自定义 View(雷达,跑马灯效果实现)

<img src="http://i.imgur.com/KaaRcj4.gif" style="zoom:30%" align=left/>    <img src="http://i.imgur.com/dquoRvh.gif" style="zoom:30%" align=right/>

<!-- more -->

## 开始

本文中使用了两种 Shader, SweepGradient 和 LinearGradient

### shader 方面知识

[https://mp.weixin.qq.com/s/pOrSG0rh7HvCNj4axe1Muw](https://mp.weixin.qq.com/s/pOrSG0rh7HvCNj4axe1Muw)

### 雷达的实现

#### 静态图实现

##### 图片分为三个部分
-	最底的圆环
-	中间的雷达
-	显示的文字
绘图顺序从上至下
#### 动态实现
-	在绘制完雷达效果之后旋转画布(使用值动画)

### 跑马灯效果实现

#### 静态图实现

##### 分为两部分
-	第一行文字
-	第二行文字
最后加上 LinearGradient 效果

#### 动态实现
-	使用值动画动态的改变 LinearGradient 的效果区域

### 其他

>onMeasure() 方法
```
private int measureHanlder(int defaultSize, int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            result = Math.min(defaultSize, specSize);
        } else {
            result = defaultSize;
        }
        return result;
    }
```
>mSweepGradient 和 LinearGradient 有很多种构造方法
>这里说明下 LinearGradient 其中一个构造方法

> **注意**
(x0,x1 的闭区间是 LinearGradient 的作用范围)
(y0,y1 可以调整 LinearGradient 的倾斜度)

>public LinearGradient(float x0, float y0, float x1, float y1, int[] colors, float[] positions,Shader.TileMode tile) 
参数:
float x0:渐变起始点x坐标 
float y0:渐变起始点y坐标 
float x1:渐变结束点x坐标
float y1:渐变结束点y坐标 
int[] colors:颜色 的int 数组
float[] positions: 相对位置的颜色数组,可为null,  若为null,可为null,颜色沿渐变线均匀分布
Shader.TileMode tile: 渲染器平铺模式

