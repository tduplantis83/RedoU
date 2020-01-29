import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'measurementConverter'
})
export class MeasurementConverterPipe implements PipeTransform {

  transform(measurement: number, from: string, to: string): number {

    // metric to us
    if(from === 'mm' && to === 'in') {
      return measurement / 25.4;
    }
    else if(from === 'mm' && to === 'ft') {
      return Math.round((measurement / 25.4) / 12);
    }
    else if(from === 'kg' && to === 'lb') {
      return measurement / 0.45359237;
    }

    //metric to metric
    else if(from === 'mm' && to === 'cm') {
      return measurement / 10;
    }
    else if(from === 'mm' && to === 'm') {
      return measurement / 1000;
    }
    else if(from === 'cm' && to === 'mm') {
      return measurement * 10;
    }
    else if(from === 'cm' && to === 'm') {
      return measurement / 100;
    }

    //us to metric
    else if(from === 'lb' && to === 'kg') {
      return measurement * 0.45359237;
    }
    //rounded for database storage
    else if(from === 'in' && to === 'mm') {
      return Math.round(measurement * 25.4);
    }

    return null;
  }

}
