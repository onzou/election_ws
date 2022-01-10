import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'bar-chart',
  template: '<div echarts [options]="option" class="demo-chart"></div>',
  styleUrls: ['bar-chart.component.scss']
})
export class BarChartComponent implements OnInit
{
  option: any;
  ngOnInit(): void 
  {
    this.initBarChart();
  }

  initBarChart()
  {

    this.option = {
      title: {
        text: 'Répartition par région',
        left: 'center'
      },
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'cross',
          crossStyle: {
            color: '#999'
          }
        }
      },
      toolbox: {
        feature: {
          dataView: { show: true, readOnly: false },
          magicType: { show: true, type: ['line', 'bar'] },
          restore: { show: true },
          saveAsImage: { show: true }
        }
      },
      legend: {
        data: ['Evaporation', 'Precipitation']
      },
      xAxis: [
        {
          type: 'category',
          data: [
            'Dakar',
            'Thies',
            'Lougou',
            'Tamba',
            'Kedougou',
            'Ziguinchor',
            'Diourbel'
          ],
          axisPointer: {
            type: 'shadow'
          }
        }
      ],
      yAxis: [
        {
          type: 'value',
          name: "",
          min: 0,
          max: 200,
          interval: 20,
          axisLabel: {
            formatter: '{value}'
          }
        }
      ],
      series: [
        {
          name: 'Votant',
          type: 'bar',
          data: [
            2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3
          ]
        },
        {
          name: 'Inscrits',
          type: 'bar',
          data: [
            2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3
          ]
        }
      ]
    };
  }
}


