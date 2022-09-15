import { Component, Input, OnInit, Output, EventEmitter, DoCheck } from '@angular/core';
import { DataTableColumns } from 'app/data-models/data-table-columns.model';

@Component({
  selector: 'app-data-table',
  templateUrl: './data-table.component.html',
  styleUrls: ['./data-table.component.css']
})
export class DataTableComponent implements OnInit, DoCheck{

  @Input() data: any[];
  @Input() columns: DataTableColumns[];
  @Input() isEditSupport: boolean;
  @Input() rowsOnPage = 5;
  @Input() isDeleteSupport: boolean;

  @Output() editEmitter: EventEmitter<any> = new EventEmitter();
  @Output() deleteEmitter: EventEmitter<any> = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

  ngDoCheck(): void {
  }

  onDeleteClicked(item: any): void {
    const isConfirmed = confirm("Are you sure about this delete?");
    if (isConfirmed) {
      this.deleteEmitter.emit(item);
    }
  }

  onEditClicked(item: any): void {
    this.editEmitter.emit(item);
  }

}
