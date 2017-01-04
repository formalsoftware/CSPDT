package com.fware.cspdt.cspm.core;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Classe principal do plug-in.
 * 
 * Contem o contexto que comunica com o Eclipse.
 * 
 * @author ALVARO, EVERALDA, FELIPE, JONATHAN, JUVENAL
 *
 */
public class CspMCorePlugin implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		CspMCorePlugin.context = bundleContext;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		CspMCorePlugin.context = null;
	}

}
