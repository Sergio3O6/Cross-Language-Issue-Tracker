using System.Windows;
using IssueTrackerClient.Views;

namespace IssueTrackerClient;

/// <summary>Entry point and composition root: the object graph is wired up here by hand.</summary>
public partial class App : Application
{
    protected override void OnStartup(StartupEventArgs e)
    {
        base.OnStartup(e);

        var window = new MainWindow();
        window.Show();
    }
}
