import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AnyCatcher } from 'rxjs/internal/AnyCatcher';
import { ResultsService } from 'src/app/services/results.service';

@Component({
  selector: 'app-visualization',
  templateUrl: './visualization.component.html',
  styleUrls: ['./visualization.component.scss']
})
export class VisualizationComponent implements OnInit 
{
  optionPie: any;
  optionBar: any;

  constructor(private results: ResultsService) { }

  ngOnInit(): void 
  {
    this.optionPie = {
      title: {
        text: 'Referer of a Website',
        subtext: 'Fake Data',
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
          name: 'Access From',
          type: 'pie',
          radius: '50%',
          data: [
            { value: 1048, name: 'Search Engine' },
            { value: 735, name: 'Direct' },
            { value: 580, name: 'Email' },
            { value: 484, name: 'Union Ads' },
            { value: 300, name: 'Video Ads' }
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

    this.optionBar = {
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
          name: "Nombre d'inscrits",
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

    this.getRegionsResults();
  }

  getRegionsResults()
  {
    this.results.getResultsInRegions()
              .subscribe((data:any)=>
              {
                console.log(data);
              },(error: HttpErrorResponse)=>
              {
                console.error(error.message);
              });
  }

}
