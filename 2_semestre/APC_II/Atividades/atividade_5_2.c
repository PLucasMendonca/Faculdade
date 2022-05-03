//Devolve o valor absoluto de X

 #include <stdio.h>

    int abs( int x )
    {
      if( x < 0 ) 
        return -x;
      else
        return x;
    }

    main()
    {
      int a;

      printf("Introduz um numero: ");
      scanf("%d", &a );
      printf("O valor absoluto de %d e %d\n", a, abs(a) );

      return 0;
    } 