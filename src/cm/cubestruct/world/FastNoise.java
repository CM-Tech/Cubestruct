package cm.cubestruct.world;

/*
* This file is part of 3DzzD http://dzzd.net/.
*
* Released under LGPL
*
* 3DzzD is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* 3DzzD is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public License
* along with 3DzzD.  If not, see <http://www.gnu.org/licenses/>.
*
* Copyright 2005 - 20010 Bruno Augier
*/


/**
 * Fast perlin noise generation
 *
 * Generate a perlin noise with 8 octave and a persistence of 0.5
 *
 * NB: 
 * - output range between 0 and 255
 * - maximum octave = 7
 * 
 * you can change type of noise between grad & value noise by commenting/uncommenting block
 * you can change type of interpolation between bicubic/bilinear by commenting/uncommenting block
 */
public class FastNoise
{
   public static int noise(double x,double y,int nbOctave)
   {
      int result=0;      
      int frequence256=256; 
      int sx=(int)((x)*frequence256); 
      int sy=(int)((y)*frequence256); 
      int octave=nbOctave;   
      while(octave!=0) 
      {
         int bX=sx&0xFF;
         int bY=sy&0xFF;

         int sxp=sx>>8;
         int syp=sy>>8;
         

         //Compute noise for each corner of current cell
         int Y1376312589_00=syp*1376312589;
         int Y1376312589_01=Y1376312589_00+1376312589;

         int XY1376312589_00=sxp+Y1376312589_00;
         int XY1376312589_10=XY1376312589_00+1;
         int XY1376312589_01=sxp+Y1376312589_01;
         int XY1376312589_11=XY1376312589_01+1;

         int XYBASE_00=(XY1376312589_00<<13)^XY1376312589_00;
         int XYBASE_10=(XY1376312589_10<<13)^XY1376312589_10;
         int XYBASE_01=(XY1376312589_01<<13)^XY1376312589_01;
         int XYBASE_11=(XY1376312589_11<<13)^XY1376312589_11;

         int alt1=(XYBASE_00 * (XYBASE_00 * XYBASE_00 * 15731 + 789221) + 1376312589) ;
         int alt2=(XYBASE_10 * (XYBASE_10 * XYBASE_10 * 15731 + 789221) + 1376312589) ;
         int alt3=(XYBASE_01 * (XYBASE_01 * XYBASE_01 * 15731 + 789221) + 1376312589) ;
         int alt4=(XYBASE_11 * (XYBASE_11 * XYBASE_11 * 15731 + 789221) + 1376312589) ;
         
         /*
          *NOTE : on  for true grandiant noise uncomment following block
          * for true gradiant we need to perform scalar product here, gradiant vector are created/deducted using
          * the above pseudo random values (alt1...alt4) : by cutting thoses values in twice values to get for each a fixed x,y vector 
          * gradX1= alt1&0xFF 
          * gradY1= (alt1&0xFF00)>>8
          *
          * the last part of the PRN (alt1&0xFF0000)>>8 is used as an offset to correct one of the gradiant problem wich is zero on cell edge
          *
          * source vector (sXN;sYN) for scalar product are computed using (bX,bY)
          *
          * each four values  must be replaced by the result of the following 
          * altN=(gradXN;gradYN) scalar (sXN;sYN)
          *
          * all the rest of the code (interpolation+accumulation) is identical for value & gradiant noise
          */
          
          
         /*START BLOCK FOR TRUE GRADIANT NOISE*/
         
          int grad1X=(alt1&0xFF)-128;
          int grad1Y=((alt1>>8)&0xFF)-128;
          int grad2X=(alt2&0xFF)-128;
          int grad2Y=((alt2>>8)&0xFF)-128;
          int grad3X=(alt3&0xFF)-128;
          int grad3Y=((alt3>>8)&0xFF)-128;
          int grad4X=(alt4&0xFF)-128;
          int grad4Y=((alt4>>8)&0xFF)-128;
          
          
          int sX1=bX>>1;
          int sY1=bY>>1;
          int sX2=128-sX1;
          int sY2=sY1;
          int sX3=sX1;
          int sY3=128-sY1;
          int sX4=128-sX1;
          int sY4=128-sY1;
          alt1=(grad1X*sX1+grad1Y*sY1)+16384+((alt1&0xFF0000)>>9); //to avoid seams to be 0 we use an offset
          alt2=(grad2X*sX2+grad2Y*sY2)+16384+((alt2&0xFF0000)>>9);
          alt3=(grad3X*sX3+grad3Y*sY3)+16384+((alt3&0xFF0000)>>9);
          alt4=(grad4X*sX4+grad4Y*sY4)+16384+((alt4&0xFF0000)>>9);
          
         /*END BLOCK FOR TRUE GRADIANT NOISE */
         
         
         /*START BLOCK FOR VALUE NOISE*/
         /*
          alt1&=0xFFFF;
          alt2&=0xFFFF;
          alt3&=0xFFFF;
          alt4&=0xFFFF;
          */
         /*END BLOCK FOR VALUE NOISE*/
         
         
         /*START BLOCK FOR LINEAR INTERPOLATION*/
         //BiLinear interpolation 
         /*
         int f24=(bX*bY)>>8;
         int f23=bX-f24;
         int f14=bY-f24;
         int f13=256-f14-f23-f24;

         int val=(alt1*f13+alt2*f23+alt3*f14+alt4*f24);
         */
         /*END BLOCK FOR LINEAR INTERPOLATION*/
         
         
         
         //BiCubic interpolation ( in the form alt(bX) = alt[n] - (3*bX^2 - 2*bX^3) * (alt[n] - alt[n+1]) )
         /*START BLOCK FOR BICUBIC INTERPOLATION*/
         int bX2=(bX*bX)>>8;
         int bX3=(bX2*bX)>>8;
         int _3bX2=3*bX2;
         int _2bX3=2*bX3;
         int alt12= alt1 - (((_3bX2 - _2bX3) * (alt1-alt2)) >> 8);
         int alt34= alt3 - (((_3bX2 - _2bX3) * (alt3-alt4)) >> 8);
         
         
         int bY2=(bY*bY)>>8;
         int bY3=(bY2*bY)>>8;
         int _3bY2=3*bY2;
         int _2bY3=2*bY3;
         int val= alt12 - (((_3bY2 - _2bY3) * (alt12-alt34)) >> 8);
         
         val*=256;
         /*END BLOCK FOR BICUBIC INTERPOLATION*/
         
         
         //Accumulate in result
         result+=(val<<octave);
         
         octave--;
         sx<<=1; 
         sy<<=1;
         
      }
      return result>>>(16+nbOctave+1);   
   }
}