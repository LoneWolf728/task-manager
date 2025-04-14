import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

/**
 * Core module containing application-wide singleton services
 * and other components that should be instantiated only once
 */
@NgModule({
  imports: [CommonModule, HttpClientModule],
})
export class CoreModule {}