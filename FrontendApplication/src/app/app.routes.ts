import { Routes } from '@angular/router';
import { EventListComponent } from './features/events/event-list/event-list';
import { EventFormComponent } from './features/events/event-form/event-form';
import { EventRegistrationComponent } from './features/events/event-registration/event-registration';
import { OrganizationListComponent } from './features/organizations/organization-list/organization-list';
import { OrganizationFormComponent } from './features/organizations/organization-form/organization-form';
import { HomeComponent } from './features/home/home';
import { AdminComponent } from './features/admin/admin-panel/admin';
import { AdminStatsComponent } from './features/admin/admin-stats/admin-stats';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'registerevent', component: EventRegistrationComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'admin/listevents', component: EventListComponent },
  { path: 'admin/createevent', component: EventFormComponent },
  { path: 'admin/editevent/:id', component: EventFormComponent },
  { path: 'admin/listorganizations', component: OrganizationListComponent },
  { path: 'admin/createorganization', component: OrganizationFormComponent },
  { path: 'admin/editorganization/:id', component: OrganizationFormComponent },
  { path: 'admin/statistics', component: AdminStatsComponent }
];
