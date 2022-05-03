#include <stdio.h>

    float max( float x, float y )
    {
      if( x > y ) 
        return x;
      else
        return y;
    }
    float max3( float x, float y, float w)
    {
      return max(x, max(y,w));
    }

    main()
    {
      float a,b,c;

      a = 22;
      b = 50;
      c = 45;
      printf("O máximo de %f, %f, e %f é %f\n", a, b, c, max3(a,b,c) );
    }