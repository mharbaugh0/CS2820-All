/**
 * Implements Visualization interface to provide methods to display 
 * visualization based on different workload choices and write them in files.
 */
package edu.uiowa.cs.warp;

import java.io.File;

/**
 * @author sgoddard
 * @version 1.5
 */
public class VisualizationImplementation implements Visualization {

  private Description visualization;
  private Description fileContent;
  private GuiVisualization window;
  private String fileName;
  private String inputFileName;
  private String fileNameTemplate;
  private FileManager fm = null;
  private WarpInterface warp = null;
  private WorkLoad workLoad = null;
  private VisualizationObject visualizationObject;

/**
 * Constructs a VisualizationImplementation object.
 * @param warp The WarpInterface object providing system data.
 * @param outputDirectory The path to the output directory for visualization files.
 * @param choice The SystemChoices value indicate which visual to create.
 */
  public VisualizationImplementation(WarpInterface warp, String outputDirectory,
      SystemChoices choice) {
    this.fm = new FileManager();
    this.warp = warp;
    inputFileName = warp.toWorkload().getInputFileName();
    this.fileNameTemplate = createFileNameTemplate(outputDirectory);
    visualizationObject = null;
    createVisualization(choice);
  }
  
  /**
   * Constructs VisualizationImplementation object,
   * @param workLoad the workload object providing workload data.
   * @param outputDirectory The path to the output directory for visualization files.
   * @param choice The WokrLoadChoice value indicate the type of visual to create.
   */

  public VisualizationImplementation(WorkLoad workLoad, String outputDirectory,
      WorkLoadChoices choice) {
    this.fm = new FileManager();
    this.workLoad = workLoad;
    inputFileName = workLoad.getInputFileName();
    this.fileNameTemplate = createFileNameTemplate(outputDirectory);
    visualizationObject = null;
    createVisualization(choice);
  }
  /**
   * Displays visual on GUI window.
   * If window is not null, set the window to visible.
   */

  @Override
  public void toDisplay() {
    // System.out.println(displayContent.toString());
    window = visualizationObject.displayVisualization();
    if (window != null) {
      window.setVisible();
    }
  }
/**
 * Writes visual content to a file using FilesManager.
 */
  @Override
  public void toFile() {
    fm.writeFile(fileName, fileContent.toString());
  }
/**
 * returns string representation of visualization.
 * @return the string representation of visual description.
 */
  @Override
  public String toString() {
    return visualization.toString();
  }
/**
 * creates a visualization based on SystemChoices
 * @param choice The SystemChoices value indicate the type of visualization.
 */
  private void createVisualization(SystemChoices choice) {
    switch (choice) { // select the requested visualization
      case SOURCE:
        createVisualization(new ProgramVisualization(warp));
        break;

      case RELIABILITIES:
        // TODO Implement Reliability Analysis Visualization
        createVisualization(new ReliabilityVisualization(warp));
        break;

      case SIMULATOR_INPUT:
        // TODO Implement Simulator Input Visualization
        createVisualization(new NotImplentedVisualization("SimInputNotImplemented"));
        break;

      case LATENCY:
        // TODO Implement Latency Analysis Visualization
        createVisualization(new LatencyVisualization(warp));
        break;

      case CHANNEL:
        // TODO Implement Channel Analysis Visualization
        createVisualization(new ChannelVisualization(warp));
        break;

      case LATENCY_REPORT:
        createVisualization(new ReportVisualization(fm, warp,
            new LatencyAnalysis(warp).latencyReport(), "Latency"));
        break;

      case DEADLINE_REPORT:
        createVisualization(
            new ReportVisualization(fm, warp, warp.toProgram().deadlineMisses(), "DeadlineMisses"));
        break;

      default:
        createVisualization(new NotImplentedVisualization("UnexpectedChoice"));
        break;
    }
  }
/**
 * Creates a visulization based on WorkLoadChoices.
 * 
 * @param choice the WorkLoadChoices value to indicate the type of visualiztion.
 */
  private void createVisualization(WorkLoadChoices choice) {
    switch (choice) { // select the requested visualization
      case COMUNICATION_GRAPH:
        // createWarpVisualization();
        createVisualization(new CommunicationGraph(fm, workLoad));
        break;

      case GRAPHVIZ:
        createVisualization(new GraphViz(fm, workLoad.toString()));
        break;

      case INPUT_GRAPH:
        createVisualization(workLoad);
        break;

      default:
        createVisualization(new NotImplentedVisualization("UnexpectedChoice"));
        break;
    }
  }
/**
 * Creates a visualization object using the provided VisualizationObject instance.
 * 
 * @param obj the Visualization instance to create visualization from.
 * @param <T> the type of parameter for the VisuzalizationObject.
 */
  private <T extends VisualizationObject> void createVisualization(T obj) {
    visualization = obj.visualization();
    fileContent = obj.fileVisualization();
    /* display is file content printed to console */
    fileName = obj.createFile(fileNameTemplate); // in output directory
    visualizationObject = obj;
  }
  /**
   * Creates the file name template for output directory based on teh input file name.
   * 
   * @param outputDirectory The path to be output directory for visualization files.
   * @return The file name template with the directory and input file name.
   */
  private String createFileNameTemplate(String outputDirectory) {
    String fileNameTemplate;
    var workingDirectory = fm.getBaseDirectory();
    var newDirectory = fm.createDirectory(workingDirectory, outputDirectory);
    // Now create the fileNameTemplate using full output path and input filename
    if (inputFileName.contains("/")) {
      var index = inputFileName.lastIndexOf("/") + 1;
      fileNameTemplate = newDirectory + File.separator + inputFileName.substring(index);
    } else {
      fileNameTemplate = newDirectory + File.separator + inputFileName;
    }
    return fileNameTemplate;
  }

}
