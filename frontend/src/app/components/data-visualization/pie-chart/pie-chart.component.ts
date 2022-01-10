import { Component, Input, OnInit } from '@angular/core';
import * as echarts from 'echarts';

@Component({
  selector: 'pie-chart',
  template: '<div echarts [options]="option" class="demo-chart"></div>',
  styleUrls: ['pie-chart.component.scss']
})
export class PieChartComponent implements OnInit
{
  option: any;

  ngOnInit():void 
  {  
    this.initData();
  }
  initData()
  {
    this.option = {
      title: {
        text: 'Résultats',
        // subtext: 'Fake Data',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      series: [
        {
          name: 'Candidat',
          type: 'pie',
          radius: '50%',
          data: [
            { value: 1048, name: 'Coumba DIOP' },
            { value: 735, name: 'Pathé NDIAYE' },
          ],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    };

  }    
}


