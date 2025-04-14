import { Pipe, PipeTransform } from '@angular/core';
import { TaskStatus } from '../../core/models/task.model';

/**
 * Pipe that formats task status enums into human-readable text
 */
@Pipe({
  name: 'statusFormat',
  standalone: true
})
export class StatusFormatPipe implements PipeTransform {
  /**
   * Transforms task status enum value to display format
   * @param value The TaskStatus enum value to transform
   * @returns Formatted status string for display
   */
  transform(value: TaskStatus): string {
    switch (value) {
      case TaskStatus.TO_DO:
        return 'To Do';
      case TaskStatus.IN_PROGRESS:
        return 'In Progress';
      case TaskStatus.DONE:
        return 'Done';
      default:
        return value;
    }
  }
}