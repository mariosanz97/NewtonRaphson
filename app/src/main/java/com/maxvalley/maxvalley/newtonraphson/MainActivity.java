package com.maxvalley.maxvalley.newtonraphson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    double a;
    double y;
    double z;
    ArrayList<Double> vectorx = new ArrayList<>();
    ArrayList<Double> vectory = new ArrayList<>();
    LineGraphSeries<DataPoint> series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        double E = 2.718281828459045;
        double Pi = 3.141592653589793;


        nRaphson(2, 3, 4);

        for (int i = 0; i < vectorx.size(); i++) {
            vectory.add(fx(vectorx.get(i), y, z));
        }

        System.out.println("El tamaño de los vectores: " + vectorx.size() + "--" + vectory.size());


        GraphView graph = (GraphView) findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();

        for (int i = 0; i < vectory.size(); i++) {
            series.appendData(new DataPoint(2 + i, 2 + i), true, 500);
            System.out.println("x: " + vectorx.get(i) + " - y: " + vectory.get(i));
        }

        graph.addSeries(series);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
        series.setThickness(8);

    }


    double fx(double x0, double y, double z) {
        return (Math.pow(x0, 3) - x0 - 1);
    }

    double dx(double x0, double y, double z) {
        return (3 * Math.pow(x0, 2) - 1);
    }

    void nRaphson(double y, double z, double x0) {

        double x1, n;

        n = 0;
        do {
            x1 = x0 - fx(x0, y, z) / dx(x0, y, z);
            n++;
            x0 = x1;
            vectorx.add(x0);
            double abss = fx(x0, y, z);
            a = Math.abs(abss);
        } while (a > 0.0010 && n <= 10000);


        System.out.println("El resultado es: " + x1);
    }
}
