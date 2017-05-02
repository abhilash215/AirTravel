package com.AirTravel;

import java.io.File;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.LazyOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 * Created by abhilashuday,Darshan Masti Prakash on 4/20/17.
 */

public class AirTravelJob {
	public static void main(String[] args) throws Exception {

		Job job = new Job();


		/* Autogenerated initialization. */
		initJob(job);
		/* Delete output folder location*/
		DeleteOutputDirectory( new File(args[1]));

		/* Custom initialization. */
		initCustom(job);

		/* Tell Task Tracker this is the main */
		job.setJarByClass(AirTravelJob.class);
		//System.out.println(args[0]);
		/* This is an example of how to set input and output. */
		FileInputFormat.setInputPaths(job, args[0]);
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		/* You can now do any other job customization. */
		// job.setXxx(...);

		/* And finally, we submit the job. */
		job.submit();

		Boolean success = job.waitForCompletion(true);

		if(success)
		{
			String file="./inputfiles/airline-safety.csv"; // generic path for csv file           
			//Safety data being loaded

			AirSafetyExtractor.setFile(file);
			AirSafetyExtractor.extraction();

			MappingCodes.LoadAirlineMap();
			MappingCodes.LoadSourceDest();
			
			//Job2
			Job job2 = new Job();
			/* Autogenerated initialization. */
			initJob2(job2);
			DeleteOutputDirectory( new File(args[2]));
			job2.setInputFormatClass(KeyValueTextInputFormat.class);
			FileInputFormat.setInputPaths(job2, new Path(args[1]));
			FileOutputFormat.setOutputPath(job2, new Path(args[2]));
			job2.submit();
			job2.waitForCompletion(true);
		}
	}


	/**
	 * This method is used to delete the output directory "output" everytime the project is run
	 */	
	public static void DeleteOutputDirectory(File index)
	{    	    	    
		File[] files = index.listFiles();
		if(files!=null)
		{
			for(File f: files)
			{
				if(f.isDirectory())
				{
					DeleteOutputDirectory(f);
				}
				else
				{
					f.delete();
				}
			}
		}
		index.delete();    	
	}

	/**
	 * This method is executed by the workflow
	 */
	public static void initCustom(Job job) {
		// Add custom initialisation here, you may have to rebuild your project before
		// changes are reflected in the workflow.
	}

	/**
	 * This method is called from within the constructor to
	 * initialize the job.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Job Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initJob
	public static void initJob(Job job) {
		org.apache.hadoop.conf.Configuration conf = job.getConfiguration();
		// Generating code using Karmasphere Protocol for Hadoop 0.20
		// CG_GLOBAL

		// CG_INPUT_HIDDEN
		job.setInputFormatClass(org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat.class);

		// CG_MAPPER_HIDDEN
		job.setMapperClass(com.AirTravel.AirTravelMapper.class);
		job.getConfiguration().set("mapred.mapper.new-api", "true");

		// CG_MAPPER
		job.getConfiguration().set("mapred.map.tasks", "3");
		job.setMapOutputKeyClass(org.apache.hadoop.io.Text.class);
		job.setMapOutputValueClass(org.apache.hadoop.io.Text.class);

		// CG_PARTITIONER_HIDDEN
		job.setPartitionerClass(org.apache.hadoop.mapreduce.lib.partition.HashPartitioner.class);

		// CG_PARTITIONER

		// CG_COMPARATOR_HIDDEN

		// CG_COMPARATOR

		// CG_COMBINER_HIDDEN

		// CG_REDUCER_HIDDEN
		job.setReducerClass(com.AirTravel.AirTravelReducer.class);
		job.getConfiguration().set("mapred.reducer.new-api", "true");

		// CG_REDUCER
		job.getConfiguration().set("mapred.reduce.tasks", "2");
		job.setOutputKeyClass(org.apache.hadoop.io.Text.class);
		job.setOutputValueClass(org.apache.hadoop.io.Text.class);

		// CG_OUTPUT_HIDDEN
		//job.setOutputFormatClass(org.apache.hadoop.mapreduce.lib.output.TextOutputFormat.class);
		LazyOutputFormat.setOutputFormatClass(job,TextOutputFormat.class);
		MultipleOutputs.addNamedOutput(job, "Reducer1Output", TextOutputFormat.class,Text.class, Text.class);

		// CG_OUTPUT

		// Others
		job.getConfiguration().set("", "");
	}

	public static void initJob2(Job job) {
		org.apache.hadoop.conf.Configuration conf = job.getConfiguration();
		// Generating code using Karmasphere Protocol for Hadoop 0.20
		// CG_GLOBAL

		// CG_INPUT_HIDDEN
		job.setInputFormatClass(org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat.class);

		// CG_MAPPER_HIDDEN
		job.setMapperClass(com.AirTravel.AirTravelMapper2.class);
		job.getConfiguration().set("mapred.mapper.new-api", "true");

		// CG_MAPPER
		job.getConfiguration().set("mapred.map.tasks", "3");
		job.setMapOutputKeyClass(org.apache.hadoop.io.Text.class);
		job.setMapOutputValueClass(org.apache.hadoop.io.Text.class);

		// CG_PARTITIONER_HIDDEN
		job.setPartitionerClass(org.apache.hadoop.mapreduce.lib.partition.HashPartitioner.class);

		// CG_PARTITIONER

		// CG_COMPARATOR_HIDDEN

		// CG_COMPARATOR

		// CG_COMBINER_HIDDEN

		// CG_REDUCER_HIDDEN
		job.setReducerClass(com.AirTravel.AirTravelReducer2.class);
		job.getConfiguration().set("mapred.reducer.new-api", "true");

		// CG_REDUCER
		job.getConfiguration().set("mapred.reduce.tasks", "2");
		job.setOutputKeyClass(org.apache.hadoop.io.Text.class);
		job.setOutputValueClass(org.apache.hadoop.io.Text.class);

		// CG_OUTPUT_HIDDEN
		//job.setOutputFormatClass(org.apache.hadoop.mapreduce.lib.output.TextOutputFormat.class);
		LazyOutputFormat.setOutputFormatClass(job,TextOutputFormat.class);
		MultipleOutputs.addNamedOutput(job, "Reducer2Output", TextOutputFormat.class,Text.class, Text.class);

		// CG_OUTPUT

		// Others
		job.getConfiguration().set("", "");
	}

}
