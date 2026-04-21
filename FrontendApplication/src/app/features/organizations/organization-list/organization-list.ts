import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { BehaviorSubject, switchMap } from 'rxjs';
import { OrganizationService } from '../../../services/organizations';

@Component({
  selector: 'app-organization-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './organization-list.html',
  styleUrl: './organization-list.scss',
})
export class OrganizationListComponent {
  private service = inject(OrganizationService);

  // new refresh metho to update a row with the same ID
  private refreshTrigger$ = new BehaviorSubject<void>(undefined);

  organizations$ = this.refreshTrigger$.pipe(switchMap(() => this.service.getAll()));

  refresh() {
    this.refreshTrigger$.next();
  }

  delete(orgId: number) {
    if (!confirm('Delete this organization?')) return;

    this.service.delete(orgId).subscribe({
      next: () => {
        this.refresh();
      },
      error: (err) => {
        console.error('Failed to delete organization', err);
        alert('An error occurred while deleting the organization.');
      },
    });
  }
}
