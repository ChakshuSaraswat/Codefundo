import java.util.*;
class maincode
{
    static int i,j;static double y[][]= new double[10][10];
    static double maxa[] = new double[10];static double avgmax,avgdist,time,wavespeed,wavespeed_shore;
    static int p[] = new int[10];double d,sendist,depth1,depth2;
    double y_shore[][]=new double[10][10];
    static double c1avgmax,c1time,c1wavespeed,c2avgmax,c2time,c2wavespeed,k,dists1;
    static char ch='Y';
    maincode()
    {
        i=0;j=0;
        avgmax=0;wavespeed=0;time=0;
        avgdist=0;d=0;sendist=0;depth1=0;depth2=0;dists1=0;
        for(i=0;i<10;i++)
            for(j=0;j<10;j++)
            {
                y[i][j]=0;
                y_shore[i][j]=0;
            }
        for(j=0;j<10;j++)
        {
            p[j]=0;
            maxa[j]=0;
        }
    }

    public static void main(String args[])
    {
        maincode obj = new maincode();
        while(ch=='Y')
        {
            obj.input();
            obj.avg_maxht();
            obj.toi();
            obj.condition();
            obj.continuation();
        }
    }

    void input()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the following data in kilometers:");
        for(i=0;i<10;i++)
        {
            System.out.println("Enter the height data from sensor row "+(i+1)+" :");
            for(j=0;j<10;j++)
                y[i][j]=sc.nextDouble();
        }
        System.out.println("Enter the distance of first sensor from the shore :");
        dists1 = sc.nextDouble();
        System.out.println("Enter the distance between two sensors :");
        sendist = sc.nextDouble();
        System.out.println("Enter the depth of sea at first sensor :");
        depth1 = sc.nextDouble();
        System.out.println("Enter the depth at 150 km the shore :");
        depth2 = sc.nextDouble();
    }

    void avg_maxht()
    {
        for(i=0;i<10;i++)
        {
            for(j=0;j<10;j++)
            {
                y_shore[i][j]=y[i][j]*(Math.pow((depth1/depth2),0.25));
            }
        }
        for(i=0;i<10;i++)
        {
            maxa[i]=y_shore[i][0];
            for(j=0;j<10;j++)
            {
                if(y[i][j]>maxa[i])
                {
                    maxa[i]=y_shore[i][j];p[i]=j;
                }
            }
        }
        for(i=0;i<10;i++)
        {
            avgmax = avgmax + maxa[i];
        }
        avgmax = avgmax/10;
    }

    void toi()
    {
        wavespeed = (Math.sqrt(9.8*depth1*1000));
        for(i=0;i<10;i++)
        {
            avgdist = avgdist + sendist*p[i];
        }
        avgdist = avgdist/10;
        avgdist=dists1+avgdist;
        wavespeed_shore = (Math.sqrt(9.8*depth2*1000))*18/5;
        time = avgdist/wavespeed_shore;
    }

    void condition()
    {
        k++;
        if(avgmax>0.003)
        {
            System.out.println("Tsunami probable");
            this.display();
        }
        else
            System.out.println("Tsunami not probable. Have a fun day at the beach.");
    }

    void display()
    {
        System.out.println("Average height of tsunami wave : "+avgmax+" km");
        System.out.println("Time of impact : "+time+" hr");
        System.out.println("Speed of wave : "+wavespeed_shore+" kmph");
        this.correction();
    }

    void correction()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Emter the correction in height of tsunami wave : ");
        c2avgmax=sc.nextDouble();
        System.out.println("Enter the correction in time of impact : ");
        c2time=sc.nextDouble();
        System.out.println("Enter the correction in speed of wave : ");
        c2wavespeed=sc.nextDouble();
        if(k==1)
        {
            c1avgmax = c2avgmax;
            c1time = c2time;
            c1wavespeed = c2wavespeed;
        }
        else
        {
            c1avgmax = (c1avgmax+c2avgmax)/2;
            c1time = (c1time+c2time)/2;
            c1wavespeed = (c1wavespeed+c2wavespeed)/2;
        }
    }

    void continuation()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Y if you want to test more cases and enter N if you do want to test more cases");
        ch = sc.next().charAt(0);
    }
}