/*
 * GWT-Ext Widget Library
 * Copyright 2007 - 2008, GWT-Ext LLC., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
 
package it.icarcnr.rainbow.client.dashboard;

import it.icarcnr.rainbow.client.util.basecomponents.ScreenPanel;
import it.icarcnr.rainbow.client.util.i18n.global.Ii18nGlobalConstants;

import com.google.gwt.core.client.GWT;
import com.gwtext.client.widgets.layout.FitLayout;

public class WelcomeView extends ScreenPanel {
	static Ii18nGlobalConstants i18nConstants = (Ii18nGlobalConstants)GWT.create(Ii18nGlobalConstants.class);

    NetworkServicesStatusContainer panelSummary;
    
    @Override
    public void init() {
		setLayout(new FitLayout());
//		setPaddings(10);
		setClosable(false);
//		setFrame(true);
		setHeaderAsText(true);
		setTitle(i18nConstants.WelcomeView_Overview()); //$NON-NLS-1$
        panelSummary= new NetworkServicesStatusContainer();
        panelSummary.init();
        add(panelSummary);
    }
    
    @Override
    public void refreshData() {
    	if(panelSummary!= null){
    		panelSummary.refreshData();
    	}
    }
}