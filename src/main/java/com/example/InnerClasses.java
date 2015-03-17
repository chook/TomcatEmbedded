package com.example;

import com.newrelic.api.agent.Trace;

public class InnerClasses {
  class Inner1
  {
    private int x = 5;
  };

  @Trace (dispatcher=true)
  public void do1() {
    int x = 1 + new Inner1().x;
    System.out.println("x is " + x);
    this.toString();

    Runnable r = new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					new Inner1();
          this.toString();
				}
				catch (Throwable e)
				{
					
				}
			}
		};
  }
}
