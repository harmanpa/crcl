/*
 * This software is public domain software, however it is preferred
 * that the following disclaimers be attached.
 * Software Copywrite/Warranty Disclaimer
 * 
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United States
 * Code this software is not subject to copyright protection and is in the
 * public domain.
 * 
 * This software is experimental. NIST assumes no responsibility whatsoever 
 * for its use by other parties, and makes no guarantees, expressed or 
 * implied, about its quality, reliability, or any other characteristic. 
 * We would appreciate acknowledgement if the software is used. 
 * This software can be redistributed and/or modified freely provided 
 * that any derivative works bear some notice that they are derived from it, 
 * and any modified versions bear some notice that they have been modified.
 * 
 *  See http://www.copyright.gov/title17/92chap1.html#105
 * 
 */
package com.github.wshackle.generate.copier.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.ResolutionScope;

/**
 * Goal which touches a timestamp file.
 */
@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_SOURCES, requiresDependencyResolution = ResolutionScope.COMPILE, requiresDependencyCollection = ResolutionScope.COMPILE, threadSafe = true)
public class GenerateMojo
        extends AbstractMojo {

    /**
     * Location of the file.
     */
    @Parameter(defaultValue = "${project.build.directory}", property = "outputDir", required = true)
    private File outputDirectory;

    @Parameter(property = "generate.classname", defaultValue = "Copier")
    private String classname;

    @Parameter(property = "generate.packagename", defaultValue = "mypackage")
    private String packagename;

    @Parameter
    private String args[];
    
    public void execute()
            throws MojoExecutionException {
        try {
            File f = outputDirectory;
            final Log log = getLog();

            log.info("outputDirectory=" + outputDirectory.getCanonicalPath());
            File genDirFile = new File(outputDirectory, "generated-sources");
            genDirFile.mkdirs();
            File copierDirFile = new File(genDirFile, "copier");
            copierDirFile.mkdirs();
            File myPackageDirFile = new File(copierDirFile, packagename);
            myPackageDirFile.mkdirs();
            File javaFile = new File(myPackageDirFile, classname +".java");
            System.out.println("javaFile = " + javaFile);

            log.info("args="+Arrays.toString(args));
            Main.log = log;
            Main.main(args);
//            try (PrintWriter pw = new PrintWriter(new FileWriter(javaFile))) {
//                pw.println("package "+packagename+";");
//                pw.println("");
//                pw.println("public class "+classname+" {");
//                pw.println("");
//                pw.println("}");
//                pw.println("");
//            }
        } catch (Exception  ex) {
            Logger.getLogger(GenerateMojo.class.getName()).log(Level.SEVERE, null, ex);
            if(ex instanceof MojoExecutionException) {
                MojoExecutionException mojoExecutionException = (MojoExecutionException) ex;
                throw mojoExecutionException;
            } else {
                throw new MojoExecutionException("this=" + this.toString(), ex);
            }
        }
    }

    @Override
    public String toString() {
        return "GenerateMojo{" + "outputDirectory=" + outputDirectory + '}';
    }

}
