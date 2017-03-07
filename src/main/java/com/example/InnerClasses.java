package com.example;

import com.newrelic.api.agent.Trace;

public class InnerClasses {
  class Inner1
  {
    private final int x = 5;
    
    public void y(int z)
    {
      int zz = z + x / 0;
    }
  };

  @Trace (dispatcher=true)
  public void do1() {
    final int x = 1 + new Inner1().x;
    System.out.println("x is " + x);
    new Inner1().y(x);
    this.toString();

    Runnable r = new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					new Inner1().y(x);
          this.toString();
				}
				catch (Throwable e)
				{
					
				}
			}
		};
  }
}
